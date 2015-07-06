package com.marcohc.android.clean.architecture.domain.interactor.inter;

import com.marcohc.android.clean.architecture.common.bus.BusProvider;
import com.marcohc.android.clean.architecture.common.exception.DataException;
import com.marcohc.android.clean.architecture.domain.bus.event.Event;

import de.greenrobot.event.EventBus;

public abstract class AsynchronousUseCase implements UseCase {

    public void execute() {
        getBus().post(createEvent());
    }

    protected abstract Event createEvent();

    protected abstract Event createResponse();

    private EventBus getBus() {
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

    protected void onEvent(DataException exception) {
        handleException(exception);
    }

    protected void handleException(DataException exception) {
        postException(exception);
        unregisterFromBus();
    }
}
