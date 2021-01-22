package xyz.gojani.jlibraries.eventbus.interfaces;

public interface Cancellable {
    void setCancelled(boolean cancelled);

    boolean isCancelled();
}
