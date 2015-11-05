package com.marcohc.android.clean.architecture.domain.interactor.inter;

import com.marcohc.android.clean.architecture.domain.bus.event.Event;

public abstract class AsynchronousUseCase extends BusHandler implements UseCase {

    public void execute() {
        getBus().post(createRequest());
    }

    protected abstract Event createRequest();

    protected abstract Event createResponse();

}
