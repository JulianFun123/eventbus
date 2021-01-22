package xyz.gojani.jlibraries.eventbus.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface EventHandler {
    int priority() default 0;

    class EventPriority {
        public static final int LOWEST = -Integer.MAX_VALUE;
        public static final int NONE = 0;
        public static final int LOW = 1;
        public static final int MIDDLE = 5;
        public static final int HIGH = 10;
        public static final int HIGHEST = Integer.MAX_VALUE;
    }
}
