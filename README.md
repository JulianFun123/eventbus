# EventBus
### A very simple EventBus for Java



## Installation
```xml
	<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>

	<dependency>
	    <groupId>com.github.julianfun123</groupId>
	    <artifactId>eventbus</artifactId>
	    <version>Tag</version>
	</dependency>
```

## Usage
```java
public class MyEvent implements Event {
}

public class MyListener implements Listener {
    @EventHandler
    public void on(MyEvent event){
        System.out.println("Test1");
    }

    @EventHandler(priority = EventHandler.EventPriority.HIGHEST)
    public void on2(MyEvent event){
        System.out.println("Test2");
    }
}

EventBus eventBus = new EventBus();
eventBus.registerListener(new MyListener());

eventBus.fire(new MyEvent());

```