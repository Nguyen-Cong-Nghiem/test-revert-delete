package vn.shippo.rider.service;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.Transaction;
import com.github.oxo42.stateless4j.StateMachineConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.shippo.rider.entity.TransportationTask;
import vn.shippo.rider.exception.TransportationTaskNotFoundException;
import vn.velacorp.Registry;

public class TransportationTaskStateMachine {
    private StateMachineConfig<State, Trigger> _config;
    private TransportationTask _entity;
    private Transaction transaction;

    public TransportationTaskStateMachine(TransportationTask entity, Transaction transaction) {
        this._entity = entity;
        this.transaction = transaction;
        _config = new StateMachineConfig<>();
        _config.configure(State.NEW)
                .permit(Trigger.onPickupStart, State.IN_PROCESS)
                .permit(Trigger.onPickupDone, State.DONE)
                .permit(Trigger.onPickupCancelled, State.CANCELLED);
        _config.configure(State.IN_PROCESS)
                .permit(Trigger.onPickupDone, State.DONE)
                .permit(Trigger.onPickupCancelled, State.CANCELLED)
                .permit(Trigger.onPickupFail, State.FAILED);
        _config.configure(State.FAILED)
                .onEntry(this::_fail);
        _config.configure(State.DONE)
                .permit(Trigger.onPickupCancelled, State.CANCELLED)
                .onEntry(this::_done);
        _config.configure(State.CANCELLED)
                .onEntry(this::_pickupCancelled);
    }

    public static State getStateConcurrent(String state) {
        switch (state) {
            case TransportationTask.State.NEW:
                return State.NEW;
            case TransportationTask.State.IN_PROCESS:
                return State.IN_PROCESS;
            case TransportationTask.State.DONE:
                return State.DONE;
            default:
                //do nothing
        }
        throw new TransportationTaskNotFoundException("State not found");
    }

    public StateMachineConfig<State, Trigger> getConfig() {
        return _config;
    }

    private void _pickupCancelled() {
        EbeanServer server = Ebean.getServer(Registry.getProperties().getProperty("datasource.rider_service"));
        UpdateTaskPickupWhenRequestCancelledService.updateTaskFromPickupRequest(_entity, server, transaction);
    }

    private void _done() {
    }

    private void _fail() {
    }

    public enum State {
        NEW, IN_PROCESS, DONE, FAILED, CANCELLED, LOST, REJECT, INTERNAL_FAILED
    }

    public enum Trigger {
        onPickupStart, onPickupFail, onPickupDone, onPickupCancelled
    }
}
