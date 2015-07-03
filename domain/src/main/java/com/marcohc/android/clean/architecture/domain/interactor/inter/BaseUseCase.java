package com.marcohc.android.clean.architecture.domain.interactor.inter;

import com.marcohc.android.clean.architecture.common.bus.BusProvider;
import com.marcohc.android.clean.architecture.common.bus.event.Event;
import com.marcohc.android.clean.architecture.common.exception.DataException;
import com.squareup.otto.Bus;

public abstract class BaseUseCase implements UseCase {

    private Bus getBus() {
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

    @Override
    public void execute() {
        getBus().post(createEvent());
    }

    protected void handleException(DataException exception) {
        postException(exception);
        unregisterFromBus();
    }
}
