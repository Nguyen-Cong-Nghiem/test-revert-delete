package vn.velacorp.eventdispatcher;

public interface IEventHandler<E extends IEvent> {
    public void on(E event);
}
