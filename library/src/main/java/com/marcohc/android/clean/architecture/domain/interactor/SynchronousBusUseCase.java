package com.marcohc.android.clean.architecture.domain.interactor;

import com.marcohc.android.clean.architecture.common.bus.BusHandler;
import com.marcohc.android.clean.architecture.common.bus.event.BusEvent;
import com.marcohc.android.clean.architecture.data.error.DataError;
import com.marcohc.android.clean.architecture.domain.error.DomainError;

/**
 * This class represents an synchronous use case connected to the bus.
 */
public abstract class SynchronousBusUseCase extends BusHandler implements UseCase {

    protected abstract BusEvent createRequest();

    public void onEventAsync(DataError dataError) {
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

    public abstract Object execute();

}
