package com.marcohc.architecture.common.bus.events.response.data;

import com.marcohc.architecture.common.bus.events.BusEvent;
import com.marcohc.architecture.data.error.DataException;

/**
 * Common methods for data responses
 */
public abstract class BaseDataResponse implements BusEvent {

    protected DataException error;

    public BaseDataResponse() {
        this.error = null;
    }

    public BaseDataResponse(DataException error) {
        this.error = error;
    }

    public boolean hasError() {
        return error != null;
    }

    public DataException getError() {
        return error;
    }

    public void setError(DataException error) {
        this.error = error;
    }

}
