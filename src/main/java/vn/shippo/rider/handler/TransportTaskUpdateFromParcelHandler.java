package vn.shippo.rider.handler;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.shippo.rider.event.ParcelUpdateEvent;
import vn.shippo.rider.event.evententity.Parcel;
import vn.shippo.rider.service.TransportationTaskService;
import vn.velacorp.Registry;
import vn.velacorp.eventdispatcher.IEventHandler;

public class TransportTaskUpdateFromParcelHandler implements IEventHandler<ParcelUpdateEvent> {
    public static final Logger logger = LoggerFactory.getLogger(TransportTaskUpdateFromParcelHandler.class.getSimpleName());

    @Override
    public void on(ParcelUpdateEvent event) {
        EbeanServer server = Ebean.getServer(Registry.getProperties().getProperty("datasource.rider_service"));
        Transaction transaction = server.beginTransaction();
        try {
            if (event.isUpdate()) {
                Parcel before = event.mappingEvent(Parcel.class, event.getBefore());
                Parcel after = event.mappingEvent(Parcel.class, event.getAfter());
                logger.info("handling event TransportTaskUpdateFromParcelHandler before:{}, after: {}", before, after);
                if (isUpdateParcelInfo(before, after)) {
                    TransportationTaskService.updateTaskFromParcels(after, transaction);
                }
            }
            transaction.commit();
            logger.info("Handler commit TransportTaskUpdateFromParcelHandler successfully!");
        } catch (Exception e) {
            transaction.rollback();
            logger.error(e.getMessage(), e);
        }
    }

    //check condition if parcel is change or not

    static boolean isUpdateParcelInfo(Parcel before, Parcel after) {
        return (isUpdateCOD(before, after)
                || isUpdateConsignee(before, after)
                || isUpdateConsigneeCode(before, after)
                || isUpdateConsigneePhone(before, after)
                || isUpdateConsigneeDetailAddress(before, after)
                || isUpdateGoods(before, after)
                || isUpdateDeliveryNotes(before, after)
                || isUpdateDeliveryLocationIdsPath(before, after)
                || isUpdatePickupNotes(before, after)
                || _isChangeTotalFee(before, after)
                || _isChangeTotalMerchantFee(before, after));
    }

    //check each information of parcel is updated or not
    private static boolean isUpdateCOD(Parcel before, Parcel after) {
        return (before.getCod() == null && after.getCod() != null)
                || (before.getCod() != null && !(before.getCod().equals(after.getCod())));
    }

    public static boolean isUpdateGoods(Parcel before, Parcel after) {
        return (before.getGoods() == null && after.getGoods() != null)
                || (before.getGoods() != null && !(before.getGoods().equals(after.getGoods())));
    }

    public static boolean isUpdatePickupNotes(Parcel before, Parcel after) {
        return (before.getPickupNotes() == null && after.getPickupNotes() != null)
                || (before.getPickupNotes() != null && !(before.getPickupNotes().equals(after.getPickupNotes())));
    }

    public static boolean isUpdateDeliveryNotes(Parcel before, Parcel after) {
        return (before.getDeliveryNotes() == null && after.getDeliveryNotes() != null)
                || (before.getDeliveryNotes() != null && !(before.getDeliveryNotes().equals(after.getDeliveryNotes())));
    }

    public static boolean isUpdateDeliveryLocationIdsPath(Parcel before, Parcel after) {
        return (before.getDeliveryLocationIdsPath() == null && after.getDeliveryLocationIdsPath() != null)
                || (before.getDeliveryLocationIdsPath() != null && !(before.getDeliveryLocationIdsPath().equals(after.getDeliveryLocationIdsPath())));
    }

    public static boolean isUpdateConsignee(Parcel before, Parcel after) {
        return (before.getConsignee() == null && after.getConsignee() != null)
                || (before.getConsignee() != null && !(before.getConsignee().equals(after.getConsignee())));
    }

    public static boolean isUpdateConsigneeCode(Parcel before, Parcel after) {
        return (before.getConsigneeCode() == null && after.getConsigneeCode() != null)
                || (before.getConsigneeCode() != null && !(before.getConsigneeCode().equals(after.getConsigneeCode())));
    }

    public static boolean isUpdateConsigneePhone(Parcel before, Parcel after) {
        return (before.getConsigneePhone() == null && after.getConsigneePhone() != null)
                || (before.getConsigneePhone() != null && !(before.getConsigneePhone().equals(after.getConsigneePhone())));
    }

    public static boolean isUpdateConsigneeDetailAddress(Parcel before, Parcel after) {
        return (before.getConsigneeDetailAddress() == null && after.getConsigneeDetailAddress() != null)
                || (before.getConsigneeDetailAddress() != null && !(before.getConsigneeDetailAddress().equals(after.getConsigneeDetailAddress())));
    }

    private static boolean _isChangeTotalFee(Parcel before, Parcel after) {
        return before.getTotalFee() != after.getTotalFee();
    }

    private static boolean _isChangeTotalMerchantFee(Parcel before, Parcel after) {
        return before.getTotalMerchantFee() != after.getTotalMerchantFee();
    }
}
