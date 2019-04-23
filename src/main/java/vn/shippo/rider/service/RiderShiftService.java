package vn.shippo.rider.service;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.Transaction;
import com.github.oxo42.stateless4j.StateMachine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.shippo.rider.entity.RiderShift;
import vn.shippo.rider.entity.TransportationTask;
import vn.shippo.rider.exception.RiderShiftNotFoundException;
import vn.velacorp.Registry;

import java.sql.Timestamp;

public class RiderShiftService {
    private static final Logger logger = LoggerFactory.getLogger(RiderShiftService.class.getSimpleName());

    public static void updatedRiderShiftFromTask(TransportationTask after, Transaction transaction) {
        RiderShift riderShift = _getRiderShift(after.getRiderShiftId());

        if (riderShift == null) throw new RiderShiftNotFoundException("Rider Shift not found!");

        if (riderShift.getState().equals(RiderShift.State.ASSIGNED) || riderShift.getState().equals(RiderShift.State.DRAFT)) {
            _checkSendTrigger(riderShift, after.getState(), transaction);
        }
    }

    //region Send trigger for RiderShiftStateMachine class
    private static void _checkSendTrigger(RiderShift riderShift, String state, Transaction transaction) {
        RiderShiftStateMachine stateMachine = new RiderShiftStateMachine(riderShift, transaction);

        StateMachine<RiderShiftStateMachine.State, RiderShiftStateMachine.Trigger> phoneCall =
                new StateMachine<>(RiderShiftStateMachine.getStateConcurrent(riderShift.getState()), stateMachine.getConfig());

        switch (state) {
            case TransportationTask.State.IN_PROCESS:
                phoneCall.fire(RiderShiftStateMachine.Trigger.onTaskStart);
                break;
            default:
                //do nothing
        }
    }
    //endregion

    static void updateStateVersionRiderShift(RiderShift riderShift, Transaction transaction) {
        EbeanServer server = Ebean.getServer(Registry.getProperties().getProperty("datasource.rider_service"));

        riderShift.setState(RiderShift.State.IN_PROCESS);
        riderShift.setVersion(riderShift.getVersion() % 2 == 0 ? riderShift.getVersion() + 2 : riderShift.getVersion() + 1);
        riderShift.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

        server.update(riderShift, transaction);
        logger.info("Updated RiderShift {} successfully!", riderShift);
    }

    private static RiderShift _getRiderShift(Integer id) {
        EbeanServer server = Ebean.getServer(Registry.getProperties().getProperty("datasource.rider_service"));
        return server.find(RiderShift.class)
                .where()
                .and()
                .eq("id", id)
                .findUnique();
    }
}
