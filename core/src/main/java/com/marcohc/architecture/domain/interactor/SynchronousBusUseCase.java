package com.marcohc.architecture.domain.interactor;

import com.marcohc.architecture.common.bus.BusHandler;
import com.marcohc.architecture.common.bus.events.BusEvent;
import com.marcohc.architecture.data.error.DataException;
import com.marcohc.architecture.domain.error.DomainException;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * This class represents a synchronous use case connected to the bus.
 */
public abstract class SynchronousBusUseCase extends BusHandler implements UseCase {

    protected abstract BusEvent createRequest();

    public abstract Object execute();

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onDataError(DataException dataException) {
        handleDataError(dataException);
    }

    /**
     * Converts the {@link DataException} to {@link DomainException} to be handle by presenter.
     * Override this method if you want to customize your data error handling
     */
    protected void handleDataError(DataException dataException) {
        DomainException appError = new DomainException(dataException.getMessage(), dataException.getCause());
        post(appError);
        unregisterFromBus();
    }

}
