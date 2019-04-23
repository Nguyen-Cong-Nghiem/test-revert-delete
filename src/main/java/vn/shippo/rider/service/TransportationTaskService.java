package vn.shippo.rider.service;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.shippo.rider.entity.PickupTaskParcel;
import vn.shippo.rider.entity.TransportationTask;
import vn.shippo.rider.event.evententity.Merchant;
import vn.shippo.rider.event.evententity.Parcel;
import vn.shippo.rider.event.evententity.PickupRequest;
import vn.shippo.rider.exception.TransportationTaskNotFoundException;
import vn.shippo.rider.util.Utils;
import vn.velacorp.Registry;

import javax.persistence.OptimisticLockException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class TransportationTaskService {
    public static final Logger logger = LoggerFactory.getLogger(TransportationTaskService.class.getSimpleName());

    public static void updateLastImportAtIntoTask(Transaction transaction, TransportationTask task, Parcel after) {
        EbeanServer server = Ebean.getServer(Registry.getProperties().getProperty("datasource.rider_service"));

        task.setLastImportAt(after.getLastImportAt());
        server.update(task, transaction);
    }

    public static void updatePickupRequestInfoIntoTask(PickupRequest before, PickupRequest after,
                                                       TransportationTask task) {
        Registry.getProperties().getProperty("datasource.rider_service");
        EbeanServer server = Ebean.getServer(Registry.getProperties().getProperty("datasource.rider_service"));

        TransportationTask taskBeforeUpdate = getTaskBeforeHandler(task);

        task.setPickupDetail(after.getPickupDetailAddress());
        task.setPickupFullAddress(after.getPickupFullAddress());
        task.setPickLocationIdsPath(after.getPickupLocationIdsPath());
        task.setPickupContactName(after.getPickupContactName());
        task.setPickupContactPhone(after.getPickupContactPhone());
        task.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        task.setVersion(task.getVersion() % 2 == 0 ? task.getVersion() + 2 : task.getVersion() + 1);
        logger.info("Set info done");
        try {
            server.update(task);
            _setParcelCountFor2Task(taskBeforeUpdate, task);
            sendEventForNotification(taskBeforeUpdate, task);
            logger.info("Update Transportation task successfully ! Task {}", task);
        }catch (OptimisticLockException e){
            logger.error("Can't update task {}", e.fillInStackTrace());
        }

    }

    private static void _updateTaskFromParcels(Parcel after, TransportationTask task) {
        task.setCod(after.getCod());
        task.setDeliverContactName(after.getConsignee());
        task.setConsigneeCode(after.getConsigneeCode());
        task.setDeliverContactPhone(after.getConsigneePhone());
        task.setDeliverDetail(after.getConsigneeDetailAddress());
        task.setDeliverFullAddress(_calculateDeliverFullAddress(after));
        task.setGoods(after.getGoods());
        task.setDeliveryNotes(after.getDeliveryNotes());
        task.setDeliverLocationIdsPath(after.getDeliveryLocationIdsPath());
        task.setDeliverFullAddress(_calculateDeliverFullAddress(after));
        task.setTotalFee(after.getTotalFee());
        task.setTotalMerchantFee(after.getTotalMerchantFee());
        task.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        task.setVersion(task.getVersion() % 2 == 0 ? task.getVersion() + 2 : task.getVersion() + 1);
    }

    private static void updateTransportationTaskIntoDatabase(TransportationTask task, Transaction transaction) {
        EbeanServer server = Ebean.getServer(Registry.getProperties().getProperty("datasource.rider_service"));
        server.update(task, transaction);
        logger.info("Update Transportation task successfully ! Task {}", task);
    }

    private static String _calculateAddressProvinceName(String pickupLocationNamesPath) {
        if (pickupLocationNamesPath == null) return null;

        String[] names = pickupLocationNamesPath.split(Pattern.quote("."));
        return names[0];
    }

    private static String _calculateAddressDistrictName(String pickupLocationNamesPath) {
        if (pickupLocationNamesPath == null) return null;

        String[] names = pickupLocationNamesPath.split(Pattern.quote("."));
        return names[1];
    }

    private static String _calculateDeliverFullAddress(Parcel afterParcel) {
        String district = _calculateAddressDistrictName(afterParcel.getDeliveryLocationNamesPath());
        String province = _calculateAddressProvinceName(afterParcel.getDeliveryLocationNamesPath());
        if (district != null && province != null) {
            return (afterParcel.getConsigneeDetailAddress() + "," + district + "," + province);
        }

        return afterParcel.getConsigneeDetailAddress();
    }

    //get Transportation task corresponding with parcel
    private static TransportationTask _getTaskByOrderId(Integer orderId) {
        EbeanServer server = Ebean.getServer(Registry.getProperties().getProperty("datasource.rider_service"));
        List<TransportationTask> tasks = server.find(TransportationTask.class)
                .where()
                .and()
                .eq("object_id", orderId)
                .eq("object_type", "CustomerDeliveryOrder")
                .endAnd()
                .orderBy().desc("created_at")
                .findList();
        if (!tasks.isEmpty()) {
            return tasks.get(0);
        } else {
            return null;
        }
    }

    //find list Transportation Tasks corresponding with PickupRequest
    public static List<TransportationTask> getTransportTaskWithRequestId(PickupRequest request) {
        EbeanServer server = Ebean.getServer(Registry.getProperties().getProperty("datasource.rider_service"));
        return server.find(TransportationTask.class)
                .where()
                .and()
                .eq("type", "PICKUP")
                .eq("request_id", request.getId()).findList();
    }

    //find list Transportation Task corresponding with Parcel barcode
    public static List<TransportationTask> getTransportTaskWithBarcode(String barcode) {
        EbeanServer server = Ebean.getServer(Registry.getProperties().getProperty("datasource.rider_service"));
        return server.find(TransportationTask.class)
                .where()
                .eq("object_code", barcode)
                .and()
                .eq("type", "DELIVER").or().eq("type", "PICKUP_AND_DELIVER")
                .endAnd()
                .findList();
    }

    //check state of Transportation Task
    public static boolean isStateTaskNewOrInProcess(TransportationTask task) {
        return (task.getState().equals(TransportationTask.State.NEW)
                || task.getState().equals(TransportationTask.State.IN_PROCESS));
    }

    public static void updateTaskFromParcels(Parcel after, Transaction transaction) {
        EbeanServer server = Ebean.getServer(Registry.getProperties().getProperty("datasource.rider_service"));
        TransportationTask task = _getTaskByOrderId(after.getOrderId());

        if (task != null && isStateTaskNewOrInProcess(task)) {
            TransportationTask taskBeforeUpdate = getTaskBeforeHandler(task);

            _updateTaskFromParcels(after, task);
            server.update(task, transaction);
            logger.info("Update Transportation task {} successfully!", task);

            _setParcelCountFor2Task(taskBeforeUpdate, task);
            sendEventForNotification(taskBeforeUpdate, task);
        }
    }

    public static void updateTaskFromMerchantHandler(Merchant after, Transaction transaction) {
        EbeanServer server = Ebean.getServer(Registry.getProperties().getProperty("datasource.rider_service"));
        List<TransportationTask> tasks = _getListTaskFromMerchant(after.getUserId());
        if (tasks == null || tasks.isEmpty())
            throw new TransportationTaskNotFoundException("Transportation Task not found!");

        List<TransportationTask> listTasksAfterUpdated = new ArrayList<>();
        for (TransportationTask task : tasks) {
            if (isStateTaskNewOrInProcess(task)) {
                TransportationTask taskBeforeUpdate = getTaskBeforeHandler(task);
                listTasksAfterUpdated.add(_updateTaskFromMerchant(task, after));
                _setParcelCountFor2Task(taskBeforeUpdate, task);
                sendEventForNotification(taskBeforeUpdate, task);
            }
        }

        if (listTasksAfterUpdated.size() > 0) {
            server.updateAll(listTasksAfterUpdated, transaction);
            logger.info("updated list TransportationTask {} successful!", listTasksAfterUpdated);
        }
    }

    static TransportationTask getTaskBeforeHandler(TransportationTask task) {
        TransportationTask taskBeforeUpdate = null;
        try {
            taskBeforeUpdate = (TransportationTask) task.clone();
        } catch (CloneNotSupportedException e) {
            logger.error(e.getMessage(), e);
        }
        return taskBeforeUpdate;
    }

    static void sendEventForNotification(TransportationTask taskBeforeUpdate, TransportationTask task) {
        String eventData = Utils._buildEventData(taskBeforeUpdate, task, null, "SYSTEM");
        Utils.sendEvent(Utils.generateMessage("Task updated", new Timestamp(System.currentTimeMillis()), eventData),
                "rider_service.topic.transportation_task.upsert");
        logger.info("Send event to kafka notification with data {} successful!", eventData);
    }

    private static void _setParcelCountFor2Task(TransportationTask taskBeforeUpdate, TransportationTask task) {
        int parcelCount = getCountTaskParcelFromTask(task);
        taskBeforeUpdate.setParcelCount(parcelCount);
        task.setParcelCount(parcelCount);
    }

    static int getCountTaskParcelFromTask(TransportationTask task) {
        EbeanServer server = Ebean.getServer(Registry.getProperties().getProperty("datasource.rider_service"));
        return server.find(PickupTaskParcel.class)
                .where()
                .and()
                .eq("task_id", task.getId())
                .findCount();
    }

    private static TransportationTask _updateTaskFromMerchant(TransportationTask task, Merchant after) {
        task.setMerchantPhone(after.getMobile());
        task.setMerchantBrand(after.getFullName());
        task.setVersion(task.getVersion() % 2 == 0 ? task.getVersion() + 2 : task.getVersion() + 1);
        task.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        return task;
    }

    private static List<TransportationTask> _getListTaskFromMerchant(Integer userId) {
        EbeanServer server = Ebean.getServer(Registry.getProperties().getProperty("datasource.rider_service"));
        return server.find(TransportationTask.class)
                .where()
                .and()
                .eq("merchant_user_id", userId)
                .findList();
    }
}
