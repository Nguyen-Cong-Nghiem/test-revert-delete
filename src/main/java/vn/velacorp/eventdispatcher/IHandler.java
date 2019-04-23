package vn.velacorp.eventdispatcher;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Provides event handler settings used by dispatchers.
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface IHandler {
    /**
     * Determines the priority in which a dispatcher executes events.
     * @return
     */
    int priority() default 0;
}
