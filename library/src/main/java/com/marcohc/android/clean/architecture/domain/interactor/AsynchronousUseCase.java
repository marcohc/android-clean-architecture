package com.marcohc.android.clean.architecture.domain.interactor;

import com.marcohc.android.clean.architecture.common.bus.BusHandler;
import com.marcohc.android.clean.architecture.common.bus.event.BusEvent;
import com.marcohc.android.clean.architecture.domain.error.DomainError;
import com.marcohc.android.clean.architecture.data.error.DataError;

/**
 * This class represents an asynchronous use case connected to the bus.
 * Always listening for {@link DataError} which occur in the data layer
 */
public abstract class AsynchronousUseCase extends BusHandler implements UseCase {

    protected abstract BusEvent createRequest();

    protected abstract BusEvent createResponse();

    /**
     * Force use case to handle data errors
     */
    protected abstract void handleDataError(DataError dataError);

    public void execute() {
        BusEvent request = createRequest();
        if (request != null) {
            post(request);
        }
    }

    public void onEventAsync(DataError dataError) {
        handleDataError(dataError);
    }

    /**
     * Converts the {@link DataError} to {@link DomainError} to be handle by presenter
     */
    protected void defaultDataErrorHandler(DataError dataError) {
        DomainError appError2 = new DomainError(dataError.getMessage(), dataError.getCode());
        postAppError(appError2);
        unregisterFromBus();
    }

}
