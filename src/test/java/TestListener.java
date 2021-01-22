import xyz.gojani.jlibraries.eventbus.annotations.EventHandler;
import xyz.gojani.jlibraries.eventbus.interfaces.Listener;

public class TestListener implements Listener {
    @EventHandler(priority = EventHandler.EventPriority.LOWEST)
    public void on(TestEvent event){
        System.out.println("Test1");
    }

    @EventHandler(priority = EventHandler.EventPriority.HIGHEST)
    public void on2(TestEvent event){
        System.out.println("Test2");
    }

    @EventHandler(priority = EventHandler.EventPriority.MIDDLE)
    public void on3(TestEvent event){
        System.out.println("Test3");
        event.setCancelled(true); // this.on(TestEvent) is not going to print Test1
    }

    @EventHandler(priority = EventHandler.EventPriority.NONE)
    public void on4(TestEvent2 event){
        System.out.println("Yea4");
    }
}
