package vn.shippo.rider.service;

import com.avaje.ebean.Transaction;
import com.github.oxo42.stateless4j.StateMachineConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.shippo.rider.entity.RiderShift;

public class RiderShiftStateMachine {
    private static final Logger _logger = LoggerFactory.getLogger(RiderShiftStateMachine.class.getSimpleName());

    private StateMachineConfig<State, Trigger> _config;

    private RiderShift _entity;
    private Transaction transaction;

    public RiderShiftStateMachine(RiderShift entity, Transaction transaction) {
        this._entity = entity;
        this.transaction = transaction;

        _config = new StateMachineConfig<>();

        _config.configure(State.DRAFT)
                .permit(Trigger.onTaskStart, State.IN_PROCESS);

        _config.configure(State.ASSIGNED)
                .permit(Trigger.onTaskStart, State.IN_PROCESS);

        _config.configure(State.IN_PROCESS)
                .onEntry(this::_inProgress);

    }

    public static State getStateConcurrent(String state) {
        switch (state) {
            case RiderShift.State.DRAFT:
                return State.DRAFT;

            case RiderShift.State.ASSIGNED:
                return State.ASSIGNED;

            default:
                //do nothing
        }

        throw new RuntimeException("State not found!");
    }

    private void _inProgress() {
        RiderShiftService.updateStateVersionRiderShift(_entity, transaction);
        RiderShiftCommentService.addNewRiderShiftComment(_entity, transaction);
    }

    public StateMachineConfig<State, Trigger> getConfig() {
        return _config;
    }

    public enum State {
        ASSIGNED, IN_PROCESS, DRAFT
    }

    public enum Trigger {
        onTaskStart
    }
}
