package com.marcohc.architecture.domain.interactor;

import com.marcohc.architecture.common.bus.BusHandler;
import com.marcohc.architecture.common.bus.events.BusEvent;
import com.marcohc.architecture.data.error.DataError;
import com.marcohc.architecture.domain.error.DomainError;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * This class represents a synchronous use case connected to the bus.
 */
public abstract class SynchronousBusUseCase extends BusHandler implements UseCase {

    protected abstract BusEvent createRequest();

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

    public abstract Object execute();

}
