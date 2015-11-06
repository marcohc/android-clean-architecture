package com.marcohc.android.clean.architecture.domain.interactor;

import com.marcohc.android.clean.architecture.common.bus.BusHandler;
import com.marcohc.android.clean.architecture.common.bus.event.BusEvent;

/**
 * Some use cases could be executed without any synchronization
 */
public abstract class SynchronousUseCase extends BusHandler implements UseCase {

    protected abstract BusEvent createRequest();

    public abstract Object execute();
}
