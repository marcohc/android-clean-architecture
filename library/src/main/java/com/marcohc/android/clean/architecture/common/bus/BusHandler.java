package com.marcohc.android.clean.architecture.common.bus;

import com.marcohc.android.clean.architecture.common.bus.event.BusEvent;
import com.marcohc.android.clean.architecture.common.exception.DataError;

public abstract class BusHandler {

    // ************************************************************************************************************************************************************************
    // * Constructor
    // ************************************************************************************************************************************************************************

    public BusHandler() {
        registerToBus();
    }

    // ************************************************************************************************************************************************************************
    // * Registration methods
    // ************************************************************************************************************************************************************************

    protected void registerToBus() {
        BusProvider.register(this);
    }

    protected void unregisterFromBus() {
        BusProvider.unregister(this);
    }

    // ************************************************************************************************************************************************************************
    // * Post methods
    // ************************************************************************************************************************************************************************

    protected void postException(DataError exception) {
        BusProvider.post(exception);
    }

    protected void postSticky(BusEvent busEvent) {
        BusProvider.postSticky(busEvent);
    }

    protected void post(BusEvent busEvent) {
        BusProvider.post(busEvent);
    }

    // ************************************************************************************************************************************************************************
    // * Exception handler methods
    // ************************************************************************************************************************************************************************

    public void onEventAsync(DataError exception) {
        handleException(exception);
    }

    protected void handleException(DataError exception) {
        postException(exception);
        unregisterFromBus();
    }
}
