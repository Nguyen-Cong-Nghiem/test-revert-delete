package vn.shippo.rider.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.shippo.rider.entity.TransportationTask;
import vn.shippo.rider.event.TransportationTaskChangeEvent;
import vn.shippo.rider.service.FirebaseTransportationTaskService;
import vn.velacorp.eventdispatcher.IEventHandler;

public class SyncTaskToFirebaseHandler implements IEventHandler<TransportationTaskChangeEvent> {
    private static final Logger logger = LoggerFactory.getLogger(SyncTaskToFirebaseHandler.class.getSimpleName());

    @Override
    public void on(TransportationTaskChangeEvent event) {
        try {
            TransportationTask after = event.mappingEvent(TransportationTask.class, event.getAfter());
            if (!after.getAssigneeType().equals(TransportationTask.AssigneeType.PARTNER)) {
                if (event.isCreate()) {
                    _handleNewTask(after);
                } else if (event.isUpdate()) {
                    _handleUpdateTask(after);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    private void _handleNewTask(TransportationTask after) {
        logger.info("handling insert event SyncTaskToFirebaseHandler after: {}", after);
        try {
            FirebaseTransportationTaskService.syncCreateFirebaseHandler(after);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    private void _handleUpdateTask(TransportationTask after) {
        logger.info("handling updated event SyncTaskToFirebaseHandler after: {}", after);
        try {
            if (TransportationTask.State.FINISHED.equals(after.getState())) {
                FirebaseTransportationTaskService.deleteTaskIfTaskInFinalStates(after);
            } else {
                FirebaseTransportationTaskService.syncUpdateFirebaseHandler(after);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
}
