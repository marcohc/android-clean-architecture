package com.marcohc.android.clean.architecture.domain.interactor;

import com.marcohc.android.clean.architecture.common.bus.BusHandler;
import com.marcohc.android.clean.architecture.common.bus.event.BusEvent;

public abstract class AsynchronousUseCase extends BusHandler implements UseCase {

    public void execute() {
        BusEvent request = createRequest();
        if (request != null) {
            post(request);
        }
    }

    protected abstract BusEvent createRequest();

    protected abstract BusEvent createResponse();

}
