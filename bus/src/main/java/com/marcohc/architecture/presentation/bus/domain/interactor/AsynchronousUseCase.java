package com.marcohc.architecture.presentation.bus.domain.interactor;

import com.marcohc.architecture.data.error.DataException;
import com.marcohc.architecture.domain.exception.DomainException;
import com.marcohc.architecture.presentation.bus.common.BusEvent;
import com.marcohc.architecture.presentation.bus.common.BusHandler;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * This class represents an asynchronous use case connected to the bus.
 * Always listening for {@link DataException} which occurs in the data layer
 */
public abstract class AsynchronousUseCase extends BusHandler implements BusUseCase {

    protected abstract BusEvent createRequest();

    protected abstract BusEvent createResponse();

    /**
     * Executes the logic of the use case
     *
     * @return null, the use case is executed asynchronously so the result will be delivered in other ways
     */
    @Override
    public Void execute() {
        BusEvent request = createRequest();
        if (request != null) {
            post(request);
        }
        return null;
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onDataException(DataException dataException) {
        handleDataException(dataException);
    }

    /**
     * Converts the {@link DataException} to {@link DomainException} to be handle by presenter.
     * Override this method if you want to customize your data error handling
     */
    protected void handleDataException(DataException dataException) {
        DomainException domainException = new DomainException(dataException.getMessage(), dataException.getCause());
        post(domainException);
        unregisterFromBus();
    }

}
