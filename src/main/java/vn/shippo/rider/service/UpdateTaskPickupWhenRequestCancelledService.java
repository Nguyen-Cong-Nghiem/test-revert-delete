package vn.shippo.rider.service;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.Transaction;
import com.github.oxo42.stateless4j.StateMachine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.shippo.rider.entity.TransportationTask;
import vn.shippo.rider.event.evententity.PickupRequest;
import vn.velacorp.Registry;

import java.sql.Timestamp;
import java.util.List;


public class UpdateTaskPickupWhenRequestCancelledService {
    private static Logger logger = LoggerFactory.getLogger(UpdateTaskPickupWhenRequestCancelledService.class.getSimpleName());

    //region method lấy list transportation task theo pickUpId
    private static List<TransportationTask> _getListTransportationTaskFromRequest(EbeanServer server, PickupRequest pickupRequest) {
        return server.find(TransportationTask.class).where()
                .eq("type", "PICKUP")
                .and()
                .eq("request_id", pickupRequest.getId())
                .endAnd()
                .findList();
    }

    //endregion
    //region method update task sau khi chuyển trạng thái
    static void updateTaskFromPickupRequest(TransportationTask transportationTask, EbeanServer server, Transaction transaction) {
        transportationTask.setState("CANCELLED");
        transportationTask.setVersion((transportationTask.getVersion() % 2) == 0 ? transportationTask.getVersion() + 2 : transportationTask.getVersion() + 1);
        transportationTask.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        server.update(transportationTask, transaction);
        logger.info("Update change state to 'CANCELLED' of task : {} successfully", transportationTask);

    }
    //endregion

    // region handler for transportationtask change state to cancelled
    public static void handlerChangeStateTaskPickupWhenPickupRequestCancelled(PickupRequest pickupRequest, Transaction transaction) {
        EbeanServer server = Ebean.getServer(Registry.getProperties().getProperty("datasource.rider_service"));
        List<TransportationTask> transportationTaskList = _getListTransportationTaskFromRequest(server, pickupRequest);
        if (transportationTaskList != null) {
            for (TransportationTask transportationTask : transportationTaskList) {
                if (_checkTaskInLastState(transportationTask)) continue;
                _checkSendTrigger(transportationTask, pickupRequest.getState(), transaction);
                TransportationTaskCommentService.insertTransportationTaskComment(transportationTask, server, transaction);
            }
        } else {
            logger.info("No have list transportation task for request_id : {} ", pickupRequest.getId());
        }

    }
    //endregion

    //region check trạng thái cuối của transportation task
    private static boolean _checkTaskInLastState(TransportationTask task) {
        return task.getState().equals(TransportationTask.State.FAILED)
                || task.getState().equals(TransportationTask.State.REJECT)
                || task.getState().equals(TransportationTask.State.CANCELLED);
    }
    //endregion

    //region method trigger use state machine
    private static void _checkSendTrigger(TransportationTask transportationTask, String state, Transaction transaction) {
        TransportationTaskStateMachine stateMachine = new TransportationTaskStateMachine(transportationTask, transaction);
        StateMachine<TransportationTaskStateMachine.State, TransportationTaskStateMachine.Trigger> phoneCall =
                new StateMachine<>(TransportationTaskStateMachine.getStateConcurrent(transportationTask.getState()),
                        stateMachine.getConfig());
        switch (state) {
            case PickupRequest.State.NEW:
                phoneCall.fire(TransportationTaskStateMachine.Trigger.onPickupStart);
                break;
            case PickupRequest.State.IN_PROCESS:
                phoneCall.fire(TransportationTaskStateMachine.Trigger.onPickupStart);
                break;
            case PickupRequest.State.DONE:
                phoneCall.fire(TransportationTaskStateMachine.Trigger.onPickupDone);
                break;
            case PickupRequest.State.CANCELLED:
                phoneCall.fire(TransportationTaskStateMachine.Trigger.onPickupCancelled);
                break;
            default:
                // do nothing

        }
    }
    //endregion
}
