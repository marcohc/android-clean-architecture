package com.marcohc.android.clean.architecture.common.bus;

import com.marcohc.android.clean.architecture.common.bus.event.BusEvent;
import com.marcohc.android.clean.architecture.common.exception.AppError;
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

    protected void post(BusEvent busEvent) {
        BusProvider.post(busEvent);
    }

    protected void postSticky(BusEvent busEvent) {
        BusProvider.postSticky(busEvent);
    }

    protected void postDataError(DataError dataError) {
        BusProvider.post(dataError);
    }

    protected void postAppError(AppError appError) {
        BusProvider.post(appError);
    }
}
