import xyz.gojani.jlibraries.eventbus.EventBus;
import xyz.gojani.jlibraries.eventbus.annotations.EventHandler;

public class Testing {
    public static void main(String[] args) {
        EventBus eventBus = new EventBus();
        eventBus.registerListener(new TestListener());
        eventBus.fire(new TestEvent2()).fire(new TestEvent());
    }
}
