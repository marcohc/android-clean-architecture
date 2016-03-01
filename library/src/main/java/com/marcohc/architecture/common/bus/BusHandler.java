package com.marcohc.architecture.common.bus;

import com.marcohc.architecture.common.bus.events.BusEvent;
import com.marcohc.architecture.data.error.DataError;
import com.marcohc.architecture.domain.error.DomainError;

/**
 * Extend from this class to get access to all useful bus methods
 */
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

    protected void postDomainError(DomainError domainError) {
        BusProvider.post(domainError);
    }
}
