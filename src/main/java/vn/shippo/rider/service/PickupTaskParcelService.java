package vn.shippo.rider.service;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.shippo.rider.entity.PickupTaskParcel;
import vn.shippo.rider.entity.TransportationTask;
import vn.shippo.rider.event.evententity.Parcel;
import vn.shippo.rider.event.evententity.PickupRequestOrder;
import vn.shippo.rider.handler.TransportTaskUpdateFromParcelHandler;
import vn.velacorp.Registry;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PickupTaskParcelService {

    public static final Logger logger = LoggerFactory.getLogger(PickupTaskParcelService.class.getSimpleName());
    private static EbeanServer riderServer = Ebean.getServer(Registry.getProperties().getProperty("datasource.rider_service"));

    public static void updateParcelInfoIntoPickupTaskParcel(List<PickupTaskParcel> beforeList,
                                                            List<PickupTaskParcel> afterList, Transaction transaction,
                                                            Parcel before, Parcel after) {
        for (PickupTaskParcel pickupTaskParcel : beforeList) {
            if (isPickupTaskParcelFinalState(pickupTaskParcel)) {
                continue;
            }
            if (TransportTaskUpdateFromParcelHandler.isUpdateGoods(before, after)) {
                pickupTaskParcel.setGoods(after.getGoods());
            }
            if (TransportTaskUpdateFromParcelHandler.isUpdatePickupNotes(before, after)) {
                pickupTaskParcel.setPickupNotes(after.getPickupNotes());
            }
            pickupTaskParcel.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
            afterList.add(pickupTaskParcel);
        }
        updatePickupTaskParcelIntoDatabase(afterList, transaction);
    }

    private static void updatePickupTaskParcelIntoDatabase(List<PickupTaskParcel> pickupTaskParcels, Transaction transaction) {
        try {
            riderServer.updateAll(pickupTaskParcels, transaction);
            logger.info("Update list Pickup Task Parcel into Database successfully !");
        } catch (Exception e) {
            logger.error("Update list Pickup Task Parcel into Database unsuccessfully");
        }
    }

    private static boolean isPickupTaskParcelFinalState(PickupTaskParcel pickupTaskParcel) {
        return (pickupTaskParcel.getState().equals(PickupTaskParcel.State.CANCELLED))
                || pickupTaskParcel.getState().equals(PickupTaskParcel.State.COMPLETED);
    }

    //region method tao description
    private static String _genPickupNote(TransportationTask transportationTask, List<Parcel> parcels) {
        if (transportationTask == null) return "";
        if (!transportationTask.getType().equals("PICKUP")) return "";
        StringBuilder description = new StringBuilder();
        for (Parcel parcel : parcels) {
            String pickupNotes = null == parcel.getPickupNotes() ? "" : parcel.getPickupNotes();
            description.append(parcel.getBarcode()).append(": ").append(pickupNotes).append("\r\n");
        }
        return description.toString();
    }
    //endregion

    //region method update task description
    private static List<TransportationTask> _getListTaskForUpdate(PickupRequestOrder pickupRequestOrder, List<Parcel> parcels) {
        List<TransportationTask> taskList = _getListTaskByPickupOrder(pickupRequestOrder);
        List<TransportationTask> taskListAfterSetValue = new ArrayList<>();
        if (taskList != null) {
            for (TransportationTask task : taskList) {
                String description = _genPickupNote(task, parcels);
                if (task.getDescription() == null) {
                    task.setDescription(description);
                    task.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
                } else {
                    if (task.getDescription().equals(description)) {
                        logger.info("Description of task not have change content with task id {} ", task.getId());
                        continue;
                    } else {
                        task.setDescription(description);
                        task.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
                    }
                }
                taskListAfterSetValue.add(task);
            }
        } else {
            logger.info("Not have list task for request_id {}", pickupRequestOrder.getRequestId());
        }
        return taskListAfterSetValue;
    }
    //endregion

    //region update task description
    public static void updateTaskDescription(PickupRequestOrder pickupRequestOrder, List<Parcel> parcelList,
                                             EbeanServer server, Transaction transaction) {
        List<TransportationTask> taskList = _getListTaskForUpdate(pickupRequestOrder, parcelList);
        server.updateAll(taskList, transaction);
        logger.info("Update description list task  {} successfully", taskList);
    }
    //endregion

    //region method lay task tu request_id, state
    private static List<TransportationTask> _getListTaskByPickupOrder(PickupRequestOrder pickupRequestOrder) {
        List<String> lastStates = Arrays.asList("DONE", "FAILED", "CANCELLED", "REJECT");
        return riderServer.find(TransportationTask.class)
                .where()
                .and()
                .eq("requestId", pickupRequestOrder.getRequestId())
                .notIn("state", lastStates)
                .findList();
    }
    //endregion

    //region method xu li de tao pickup_task_parcel
    public static void handleCreatePickupTaskParcel(PickupRequestOrder pickupRequestOrder, List<Parcel> parcelList,
                                                    EbeanServer server, Transaction transaction) {
        List<TransportationTask> tasks = _getListTaskByPickupOrder(pickupRequestOrder);
        if (tasks == null) {
            logger.info("Not have transportation task with request_id {} ", pickupRequestOrder.getRequestId());
        } else {
            Parcel parcel = _getParcelByOrderId(parcelList, pickupRequestOrder);
            for (TransportationTask task : tasks) {
                _createPickupTaskParcel(task, parcel, server, transaction);
            }

        }

    }
    //endregion

    //region method ger parcel by order_id of pickup_order
    private static Parcel _getParcelByOrderId(List<Parcel> parcelList, PickupRequestOrder pickupRequestOrder) {
        Parcel newParcel = new Parcel();
        for (Parcel parcel : parcelList) {
            if (parcel.getId().equals(pickupRequestOrder.getOrderId())) {
                newParcel = parcel;
                break;
            }
        }
        return newParcel;
    }
    //endregion


    //region insert new pickup_task_parcel
    private static void _createPickupTaskParcel(TransportationTask task, Parcel parcel, EbeanServer server,
                                                Transaction transaction) {
        if (task.getType().equals("PICKUP")) {
            if (task.getState().equals("NEW") || task.getState().equals("IN_PROCESS")) {
                PickupTaskParcel pickupTaskParcel = new PickupTaskParcel();
                pickupTaskParcel.setTaskId(task.getId());
                pickupTaskParcel.setBarcode(parcel.getBarcode());
                pickupTaskParcel.setState(parcel.getState());
                pickupTaskParcel.setPickupNotes(parcel.getPickupNotes());
                pickupTaskParcel.setGoods(parcel.getGoods());
                pickupTaskParcel.setCreatedAt(new Timestamp(System.currentTimeMillis()));
                pickupTaskParcel.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
                server.insert(pickupTaskParcel, transaction);
                logger.info("Created pickupTaskParcel successfully, pickupTaskParcel with task_id {} and barcode {}", pickupTaskParcel.getTaskId(), pickupTaskParcel.getBarcode());

                _sendEventForNotification(task);
            }
        }
    }

    private static void _sendEventForNotification(TransportationTask task) {
        TransportationTask taskBefore = TransportationTaskService.getTaskBeforeHandler(task);

        int parcelCount = TransportationTaskService.getCountTaskParcelFromTask(task);
        taskBefore.setParcelCount(parcelCount);
        task.setParcelCount(parcelCount + 1);
        TransportationTaskService.sendEventForNotification(taskBefore, task);
    }
    //endregion

    static List<PickupTaskParcel> getListParcelsInPickupTask(Integer task_id) {
        EbeanServer server = Ebean.getServer(Registry.getProperties().getProperty("datasource.rider_service"));
        return server.find(PickupTaskParcel.class)
                .where()
                .and()
                .eq("taskId", task_id)
                .findList();
    }

}
