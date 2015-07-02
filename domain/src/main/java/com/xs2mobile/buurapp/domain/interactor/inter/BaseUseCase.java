package com.xs2mobile.buurapp.domain.interactor.inter;

import com.squareup.otto.Bus;
import com.xs2mobile.buurapp.data.datasource.DataError;
import com.xs2mobile.buurapp.domain.bus.BusProvider;
import com.xs2mobile.buurapp.domain.bus.event.Event;

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

    protected void postError(DataError error) {
        getBus().post(error);
    }

    protected void post(Event event) {
        getBus().post(event);
    }

    @Override
    public void execute() {
        getBus().post(createEvent());
    }
}
