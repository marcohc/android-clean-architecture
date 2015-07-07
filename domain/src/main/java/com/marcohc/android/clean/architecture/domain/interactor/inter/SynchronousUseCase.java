package com.marcohc.android.clean.architecture.domain.interactor.inter;

import com.marcohc.android.clean.architecture.domain.bus.event.Event;

/**
 * Some use cases could be executed without any synchronization
 */
public abstract class SynchronousUseCase extends BusHandler implements UseCase {

    protected abstract Event createRequest();

    public abstract Object execute();
}
