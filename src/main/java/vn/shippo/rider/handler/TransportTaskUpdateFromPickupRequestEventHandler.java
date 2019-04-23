package vn.shippo.rider.handler;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.shippo.rider.entity.TransportationTask;
import vn.shippo.rider.event.PickupRequestUpdateEvent;
import vn.shippo.rider.event.evententity.PickupRequest;
import vn.shippo.rider.service.TransportationTaskService;
import vn.velacorp.Registry;
import vn.velacorp.eventdispatcher.IEventHandler;

import java.util.List;

public class TransportTaskUpdateFromPickupRequestEventHandler implements IEventHandler<PickupRequestUpdateEvent> {
    public static final Logger logger
            = LoggerFactory.getLogger(TransportTaskUpdateFromPickupRequestEventHandler.class.getSimpleName());
    private static EbeanServer riderServer = Ebean.getServer(Registry.getProperties().getProperty("datasource.rider_service"));

    @Override
    public void on(PickupRequestUpdateEvent event) {
        Transaction transaction = riderServer.beginTransaction();
        try {
            if (event.isUpdate()) {
                PickupRequest before = event.mappingEvent(PickupRequest.class, event.getBefore());
                PickupRequest after = event.mappingEvent(PickupRequest.class, event.getAfter());
                logger.info("handling event TransportTaskUpdateFromPickupRequestEventHandler before:{}, after: {}", before, after);
                if (isUpdatePickupRequestInfoToUpdateTask(before, after)) {
                    List<TransportationTask> tasks = TransportationTaskService.getTransportTaskWithRequestId(after);
                    if (tasks == null || tasks.isEmpty()) {
                        logger.info("There are no Transportation Tasks suitable with PickupRequest {} condition", after.getId());
                    } else {
                        for (TransportationTask task : tasks) {
                            if (!TransportationTaskService.isStateTaskNewOrInProcess(task)) continue;
                            TransportationTaskService.updatePickupRequestInfoIntoTask(before, after, task);
                        }
                    }
                }
            }
            transaction.commit();
            logger.info("Transaction committed");
        } catch (Exception e) {
            transaction.rollback();
            logger.error(e.getMessage(), e);
        }
    }

    //check condition if pickupRequest is change or not
    private static boolean isUpdatePickupRequestInfoToUpdateTask(PickupRequest before, PickupRequest after) {
        return (isUpdatePickUpDetailAddress(before, after)
                || isUpdatePickupFullAddress(before, after)
                || isUpdatePickupLocationIdsPath(before, after)
                || isUpdatePickupContactName(before, after)
                || isUpdatePickupContactPhone(before, after));
    }

    //check each information of pickupRequest is updated or note
    private static boolean isUpdatePickUpDetailAddress(PickupRequest before, PickupRequest after) {
        return (before.getPickupDetailAddress() == null && after.getPickupDetailAddress() != null)
                || (before.getPickupDetailAddress() != null
                && (!(before.getPickupDetailAddress().equals(after.getPickupDetailAddress()))));
    }

    private static boolean isUpdatePickupFullAddress(PickupRequest before, PickupRequest after) {
        return (before.getPickupFullAddress() == null && after.getPickupFullAddress() != null)
                || (before.getPickupFullAddress() != null
                && (!(before.getPickupFullAddress().equals(after.getPickupFullAddress()))));
    }

    private static boolean isUpdatePickupLocationIdsPath(PickupRequest before, PickupRequest after) {
        return (before.getPickupLocationIdsPath() == null && after.getPickupLocationIdsPath() != null)
                || (before.getPickupLocationIdsPath() != null
                && (!(before.getPickupLocationIdsPath().equals(after.getPickupLocationIdsPath()))));
    }

    private static boolean isUpdatePickupContactName(PickupRequest before, PickupRequest after) {
        return (before.getPickupContactName() == null && after.getPickupContactName() != null)
                || (before.getPickupContactName() != null
                && (!(before.getPickupContactName().equals(after.getPickupContactName()))));
    }

    private static boolean isUpdatePickupContactPhone(PickupRequest before, PickupRequest after) {
        return (before.getPickupContactPhone() == null && after.getPickupContactPhone() != null)
                || (before.getPickupContactPhone() != null
                && (!(before.getPickupContactPhone().equals(after.getPickupContactPhone()))));
    }

}
