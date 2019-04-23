package vn.shippo.rider.handler;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.shippo.rider.entity.TransportationTask;
import vn.shippo.rider.event.TransportationTaskChangeStateEvent;
import vn.shippo.rider.service.RiderShiftService;
import vn.velacorp.Registry;
import vn.velacorp.eventdispatcher.IEventHandler;

public class UpdatedRiderShiftFromTaskHandler implements IEventHandler<TransportationTaskChangeStateEvent> {
    private static final Logger logger = LoggerFactory.getLogger(UpdatedRiderShiftFromTaskHandler.class.getSimpleName());

    @Override
    public void on(TransportationTaskChangeStateEvent event) {
        EbeanServer server = Ebean.getServer(Registry.getProperties().getProperty("datasource.rider_service"));
        Transaction trans = server.beginTransaction();
        try {
            if (event.isUpdate()) {
                TransportationTask before = event.mappingEvent(TransportationTask.class, event.getBefore());
                TransportationTask after = event.mappingEvent(TransportationTask.class, event.getAfter());
                logger.info("handling event UpdatedRiderShiftFromTaskHandler before:{}, after: {}", before, after);

                if (before.getType().equals(TransportationTask.Type.DELIVER) && _isStateNewToInProcess(before, after)) {
                    RiderShiftService.updatedRiderShiftFromTask(after, trans);
                }
            }
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            logger.error(e.getMessage(), e);
        }
    }

    private boolean _isStateNewToInProcess(TransportationTask before, TransportationTask after) {
        return before.getState().equals(TransportationTask.State.NEW)
                && after.getState().equals(TransportationTask.State.IN_PROCESS);
    }
}
