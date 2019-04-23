package vn.shippo.rider.handler;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.shippo.rider.entity.TransportationTask;
import vn.shippo.rider.event.ParcelUpdateEvent;
import vn.shippo.rider.event.evententity.Parcel;
import vn.shippo.rider.service.TransportationTaskService;
import vn.velacorp.Registry;
import vn.velacorp.eventdispatcher.IEventHandler;

import java.util.List;

public class TransportationTaskSyncLastImportAtFromParcel implements IEventHandler<ParcelUpdateEvent> {

    public static final Logger logger = LoggerFactory.getLogger(TransportationTaskSyncLastImportAtFromParcel.class.getSimpleName());

    @Override
    public void on(ParcelUpdateEvent event) {
        EbeanServer server = Ebean.getServer(Registry.getProperties().getProperty("datasource.rider_service"));
        Transaction transaction = server.beginTransaction();
        try {
            if (event.isUpdate()) {
                Parcel before = event.mappingEvent(Parcel.class, event.getBefore());
                Parcel after = event.mappingEvent(Parcel.class, event.getAfter());
                if (isUpdateLastImportAt(before, after)) {
                    List<TransportationTask> tasks = TransportationTaskService.getTransportTaskWithBarcode(after.getBarcode());
                    if (tasks == null || tasks.isEmpty()) {
                        logger.info("There are no Transportation Tasks suitable with Parcel barcode {} condition", after.getBarcode());
                    } else {
                        for (TransportationTask task : tasks) {
                            TransportationTaskService.updateLastImportAtIntoTask(transaction, task, after);
                        }
                    }
                }
            }
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            logger.error(e.getMessage(), e);
        }
    }

    private static boolean isUpdateLastImportAt(Parcel before, Parcel after){
        return (before.getLastImportAt() == null && after.getLastImportAt() != null)
                || (before.getLastImportAt() != null && (!before.getLastImportAt().equals(after.getLastImportAt())));
    }
}
