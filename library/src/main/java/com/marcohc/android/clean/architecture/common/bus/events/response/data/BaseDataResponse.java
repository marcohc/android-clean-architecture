package com.marcohc.android.clean.architecture.common.bus.events.response.data;

import com.marcohc.android.clean.architecture.common.bus.events.BusEvent;
import com.marcohc.android.clean.architecture.data.error.DataError;

/**
 * Common methods for data responses
 */
public abstract class BaseDataResponse implements BusEvent {

    protected DataError error;

    public BaseDataResponse() {
        this.error = null;
    }

    public BaseDataResponse(DataError error) {
        this.error = error;
    }

    public boolean hasError() {
        return error != null;
    }

    public DataError getError() {
        return error;
    }

    public void setError(DataError error) {
        this.error = error;
    }

}
