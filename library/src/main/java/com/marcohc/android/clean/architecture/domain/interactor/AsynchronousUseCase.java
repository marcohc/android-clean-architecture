package com.marcohc.android.clean.architecture.domain.interactor;

import com.marcohc.android.clean.architecture.common.bus.BusHandler;
import com.marcohc.android.clean.architecture.common.bus.event.BusEvent;
import com.marcohc.android.clean.architecture.common.exception.AppError;
import com.marcohc.android.clean.architecture.common.exception.DataError;

public abstract class AsynchronousUseCase extends BusHandler implements UseCase {

    public void onEventMainThread(DataError dataError) {
        AppError appError = new AppError(dataError.getMessage(), dataError.getCode());
        postAppError(appError);
        unregisterFromBus();
    }

    public void execute() {
        BusEvent request = createRequest();
        if (request != null) {
            post(request);
        }
    }

    protected abstract BusEvent createRequest();

    protected abstract BusEvent createResponse();

}
