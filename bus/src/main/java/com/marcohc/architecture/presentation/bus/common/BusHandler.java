package com.marcohc.architecture.presentation.bus.common;

/**
 * Extend from this class to get access to all useful bus methods
 */
public abstract class BusHandler {

    public BusHandler() {
        registerToBus();
    }

    protected void registerToBus() {
        BusProvider.register(this);
    }

    protected void unregisterFromBus() {
        BusProvider.unregister(this);
    }

    protected void post(Object busEvent) {
        BusProvider.post(busEvent);
    }

    protected void postSticky(Object busEvent) {
        BusProvider.postSticky(busEvent);
    }
}