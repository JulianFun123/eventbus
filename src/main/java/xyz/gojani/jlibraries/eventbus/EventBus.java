package xyz.gojani.jlibraries.eventbus;

import xyz.gojani.jlibraries.eventbus.interfaces.Cancellable;
import xyz.gojani.jlibraries.eventbus.interfaces.Event;
import xyz.gojani.jlibraries.eventbus.annotations.EventHandler;
import xyz.gojani.jlibraries.eventbus.interfaces.Listener;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.*;

public class EventBus {

    private List<AbstractMap.SimpleEntry<Listener, Method>> eventHandlers;
    public EventBus(){
        eventHandlers = new ArrayList<>();
    }


    public EventBus fire(Event event){

        Class eventClass = event.getClass();
        eventHandlers
            .stream()
            .filter(entry -> entry.getValue().getParameters()[0].getType() == eventClass)
            .sorted(Comparator.comparingInt(entry -> entry.getValue().getAnnotation(EventHandler.class).priority() * -1))
            .forEach(entry -> {
                if (event instanceof Cancellable && ((Cancellable) event).isCancelled()) return;
                Method method = entry.getValue();
                EventHandler eventHandler = method.getAnnotation(EventHandler.class);

                try {
                    if (!method.isAccessible())
                        method.setAccessible(true);
                    method.invoke(entry.getKey(), event);

                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }

                //  method.invoke()
            });

        return this;
    }

    public EventBus registerListener(Listener listener){

        Class clazz = listener.getClass();

        for (Method declaredMethod : clazz.getDeclaredMethods()) {
            EventHandler eventHandler = declaredMethod.getAnnotation(EventHandler.class);

            if (eventHandler != null)
                eventHandlers.add(new AbstractMap.SimpleEntry<>(listener, declaredMethod));
        }

        return this;
    }

    /*
    // TODO
    public <T extends Event> EventBus on(Class<T> event, EventCallable eventCallable){

        Listener listener = new Listener() {
            @EventHandler
            public void fakeEvent(T event) {
                eventCallable.on(event);
            }
        };

        Method method = listener.getClass().getDeclaredMethods()[0];

        eventHandlers.add(new AbstractMap.SimpleEntry<>(listener, method));

        return this;
    }
    */

    public List<AbstractMap.SimpleEntry<Listener, Method>> getEventHandlers() {
        return eventHandlers;
    }

    public interface EventCallable {
        void on(Event event);
    }

}
