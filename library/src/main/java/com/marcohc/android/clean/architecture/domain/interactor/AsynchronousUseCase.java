package com.marcohc.android.clean.architecture.domain.interactor;

import com.marcohc.android.clean.architecture.common.bus.BusHandler;
import com.marcohc.android.clean.architecture.common.bus.events.BusEvent;
import com.marcohc.android.clean.architecture.domain.error.DomainError;
import com.marcohc.android.clean.architecture.data.error.DataError;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * This class represents an asynchronous use case connected to the bus.
 * Always listening for {@link DataError} which occur in the data layer
 */
public abstract class AsynchronousUseCase extends BusHandler implements UseCase {

    protected abstract BusEvent createRequest();

    protected abstract BusEvent createResponse();

    public void execute() {
        BusEvent request = createRequest();
        if (request != null) {
            post(request);
        }
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onDataError(DataError dataError) {
        handleDataError(dataError);
    }

    /**
     * Converts the {@link DataError} to {@link DomainError} to be handle by presenter.
     * Override this method if you want to customize your data error handling
     */
    protected void handleDataError(DataError dataError) {
        DomainError appError = new DomainError(dataError.getMessage(), dataError.getCode());
        postAppError(appError);
        unregisterFromBus();
    }

}
