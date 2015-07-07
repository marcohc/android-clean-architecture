package com.marcohc.android.clean.architecture.domain.interactor.inter;

import com.marcohc.android.clean.architecture.domain.bus.event.Event;

public abstract class AsynchronousUseCase extends BusHandler implements UseCase {

    public abstract void execute();

    protected abstract Event createRequest();

    protected abstract Event createResponse();

}
