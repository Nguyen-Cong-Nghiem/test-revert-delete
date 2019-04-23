package vn.shippo.rider.service;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.shippo.rider.entity.PickupTaskParcel;
import vn.shippo.rider.entity.Rider;
import vn.shippo.rider.entity.TransportationTask;
import vn.shippo.rider.entity.firebase.FirebaseTransportationTask;
import vn.shippo.rider.firebase.Firebase;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FirebaseTransportationTaskService {
    private static Logger logger = LoggerFactory.getLogger(FirebaseTransportationTaskService.class.getSimpleName());
    private static Firebase firebase;

    static {
        try {
            firebase = new Firebase();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    public static void syncCreateFirebaseHandler(TransportationTask after) {
        Map<String, FirebaseTransportationTask> transportationTaskMap = new HashMap<>();
        String taskId = String.valueOf(after.getId());
        try {
            FirebaseTransportationTask firebaseTransportationTask = _mapTaskToFirebaseTask(after);
            transportationTaskMap.put(taskId, firebaseTransportationTask);
            firebase.update(transportationTaskMap
                    , String.format("%s%s", "shippo/transportation_tasks/", firebaseTransportationTask.getAssignee()));
            logger.info("Insert sync TransportationTask {} to firebase successfully!", after);
        } catch (Exception e) {
            logger.error("insert task to firebase failed, error {}", e);
        }
    }

    public static void deleteTaskIfTaskInFinalStates(TransportationTask after) {
        String taskId = String.valueOf(after.getId());
        try {
            firebase.remove(String.format("%s%s%s%s",
                    "shippo/transportation_tasks/", getUserIdByRiderId(after.getAssignee()), "/", taskId));
            logger.info("Deleted TransportationTask {} in firebase successfully!", after);
        } catch (Exception e) {
            logger.error("delete task to firebase failed, error {}", e);
        }
    }

    public static void syncUpdateFirebaseHandler(TransportationTask after) {
        Map<String, FirebaseTransportationTask> transportationTaskMap = new HashMap<>();
        String taskId = String.valueOf(after.getId());
        try {
            FirebaseTransportationTask firebaseTransportationTask = _mapTaskToFirebaseTask(after);
            firebaseTransportationTask.setIsUpdate(true);
            transportationTaskMap.put(taskId, firebaseTransportationTask);
            firebase.update(transportationTaskMap,
                    String.format("%s%s","shippo/transportation_tasks/", firebaseTransportationTask.getAssignee()));
            logger.info("Updated sync TransportationTask {} to firebase successfully!", after);
        } catch (Exception e) {
            logger.error("update task to firebase failed, error {}", e);
        }
    }

    private static Integer getUserIdByRiderId(Integer assignee) {
        Integer uid = assignee;
        Rider rider = RiderService.getRiderById(assignee);

        if (rider == null) {
            logger.info("Rider with id {} not found!!!", assignee);
        } else {
            uid = rider.getUserId();
        }
        return uid;
    }

    private static FirebaseTransportationTask _mapTaskToFirebaseTask(TransportationTask after) {
        FirebaseTransportationTask firebaseTask = new FirebaseTransportationTask();

        //region copy properties from transportation_tasks to firebase used BeanUtils
        try {
            BeanUtils.copyProperties(firebaseTask, after);
        } catch (IllegalAccessException | InvocationTargetException e) {
            logger.error(e.getMessage(), e);
        }
        //endregion
        firebaseTask.setAssignee(getUserIdByRiderId(after.getAssignee()));
        firebaseTask.setIsUpdate(false);
        if (after.getType().equals(TransportationTask.Type.PICKUP)) {
            _setValueIfTaskTypeIsPickup(firebaseTask, after);
        } else if (after.getType().equals(TransportationTask.Type.DELIVER)) {
            _setValueIfTaskTypeIsDeliver(firebaseTask, after);
        }

        return firebaseTask;
    }

    private static void _setValueIfTaskTypeIsPickup(FirebaseTransportationTask firebaseTask, TransportationTask after) {
        Map<String, PickupTaskParcel> taskParcelsMap = new HashMap<>();
        List<PickupTaskParcel> taskParcels = PickupTaskParcelService.getListParcelsInPickupTask(after.getId());
        if (taskParcels != null && !taskParcels.isEmpty()) {
            for (PickupTaskParcel parcel : taskParcels) {
                taskParcelsMap.put(parcel.getBarcode(), parcel);
            }
        }
        firebaseTask.setParcels(taskParcelsMap);
    }

    private static void _setValueIfTaskTypeIsDeliver(FirebaseTransportationTask firebaseTask, TransportationTask after) {
        firebaseTask.setTotalFee(after.getTotalFee());
        firebaseTask.setTotalMerchantFee(after.getTotalMerchantFee());
    }
}
