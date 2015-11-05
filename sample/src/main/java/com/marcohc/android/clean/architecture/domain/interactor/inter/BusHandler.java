package com.marcohc.android.clean.architecture.domain.interactor.inter;

import com.marcohc.android.clean.architecture.bus.BusProvider;
import com.marcohc.android.clean.architecture.exception.DataException;
import com.marcohc.android.clean.architecture.domain.bus.event.Event;

import de.greenrobot.event.EventBus;

public abstract class BusHandler {

    public BusHandler() {
        registerToBus();
    }

    protected EventBus getBus() {
        return BusProvider.getInstance();
    }

    protected void registerToBus() {
        getBus().register(this);
    }

    protected void unregisterFromBus() {
        getBus().unregister(this);
    }

    protected void postException(DataException exception) {
        getBus().post(exception);
    }

    protected void post(Event event) {
        getBus().post(event);
    }

    protected void onEventAsync(DataException exception) {
        handleException(exception);
    }

    protected void handleException(DataException exception) {
        postException(exception);
        unregisterFromBus();
    }
}
