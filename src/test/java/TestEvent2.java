import xyz.gojani.jlibraries.eventbus.interfaces.Cancellable;
import xyz.gojani.jlibraries.eventbus.interfaces.Event;

public class TestEvent2 implements Event, Cancellable {

    private boolean cancelled = false;

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
