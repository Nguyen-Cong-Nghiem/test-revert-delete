package vn.velacorp.eventdispatcher;

import java.util.List;

public interface IDispatcher {
    /**
     * Dispatch an event with this dispatcher.
     *
     * @param event     Event to dispatch
     * @return          the event after all associated event handlers have been executed
     */
    public <E extends IEvent> E dispatch(E event);

    /** Resets this dispatcher */
    public void unregisterAll();

    /** Register a event listener */
    public <E extends IEvent> void register(Class<E> eventType, IEventHandler handler);

    /** unregister a handler */
    public <E extends IEvent> boolean unregister(Class<E> type, IEventHandler handler);

    /** unregister all handlers of a event */
    public List<IEventHandler> unregister(Class<? extends IEvent> type);
}