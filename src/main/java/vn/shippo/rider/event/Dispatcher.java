package vn.shippo.rider.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.velacorp.eventdispatcher.IDispatcher;
import vn.velacorp.eventdispatcher.IEvent;
import vn.velacorp.eventdispatcher.IEventHandler;
import vn.velacorp.eventdispatcher.IHandler;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Dispatcher implements IDispatcher {
    private Logger logger = LoggerFactory.getLogger("Application");

    private final ExecutorService _executor;

    private static final HandlerPriorityComparator _priority = new HandlerPriorityComparator();

    private final Map<Class<? extends IEvent>, List<IEventHandler>> _listener = new HashMap<>();

    public Dispatcher(int threadsNo) {
        _executor = Executors.newFixedThreadPool(threadsNo);
    }

    public Map<Class<? extends IEvent>, List<IEventHandler>> getListener() {
        return _listener;
    }

    public <T extends IEvent> List<IEventHandler> getHandlers(Class<T> type) {
        if (!_listener.containsKey(type)) {
            _listener.put(type, new ArrayList<>());
        }

        return _listener.get(type);
    }

    /**
     * Unregisters the IEventHandler from the specified IEvent type registered with this dispatcher.
     *
     * @param type    IEvent type to unregister the IEventHandler from
     * @param handler IEventHandler to unregister
     * @return {@code true} if the IEventHandler was previously registered
     */
    public <E extends IEvent> boolean unregister(Class<E> type, IEventHandler handler) {
        return _listener.containsKey(type) && getHandlers(type).remove(handler);
    }

    /**
     * Unregisters every IEventHandler registered for the specified IEvent type.
     *
     * @param type IEvent type to unregister associated IEventHandlers from
     * @return List of IEventHandlers previously registered to the specified IEvent type
     */
    public List<IEventHandler> unregister(Class<? extends IEvent> type) {
        if (_listener.containsKey(type)) {
            return _listener.remove(type);
        }

        return null;
    }

    /**
     * Unregisters every registered {@link IEvent}.
     */
    @Override
    public void unregisterAll() {
        _listener.clear();
    }

    @Override
    public <E extends IEvent> void register(Class<E> eventType, IEventHandler handler) {
        List<IEventHandler> handlers = getHandlers(eventType);

        handlers.add(handler);
        handlers.sort(_priority);
    }

    public void shutdown() {
        if (_executor != null) {
            _executor.shutdown();
            try {
                if (!_executor.awaitTermination(5000, TimeUnit.MILLISECONDS)) {
                    logger.error("Timed out waiting for consumer threads to shut down, exiting uncleanly");
                }
            } catch (InterruptedException e) {
                logger.error("Interrupted during shutdown, exiting uncleanly {}", e);
            }
        }
    }

    /**
     * Dispatch an IEvent to each {@link IEventHandler} for the IEvent type registered with this dispatcher.
     */
    @SuppressWarnings("unchecked")
    @Override
    public <E extends IEvent> E dispatch(E event) {
        getHandlers(event.getClass()).forEach(handler -> {
            logger.info(String.format("Dispatch event %s to %s handler", event.getClass().getName(),
                    handler.getClass().getName()));

            _executor.submit(() -> {
                handler.on(event);
            });
        });
        return event;
    }

    private static int getHandlerPriority(IEventHandler handler) {
        int priority = 0;

        try {
            if (handler.getClass().getAnnotation(IHandler.class) != null) {
                priority = handler.getClass().getAnnotation(IHandler.class).priority();
            } else {
                priority = handler.getClass().getMethod("execute", IEvent.class).getAnnotation(IHandler.class).priority();
            }
        } catch (Exception ignored) {
        }

        return priority;
    }

    private static class HandlerPriorityComparator implements Comparator<IEventHandler> {
        @Override
        public int compare(IEventHandler one, IEventHandler two) {
            return getHandlerPriority(one) - getHandlerPriority(two);
        }
    }
}