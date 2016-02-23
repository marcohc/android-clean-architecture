package com.marcohc.android.clean.architecture.common.bus.events.response.domain;

import com.marcohc.android.clean.architecture.common.bus.events.BusEvent;
import com.marcohc.android.clean.architecture.presentation.error.AppError;

/**
 * Common methods for domain responses
 */
public abstract class BaseDomainResponse implements BusEvent {

    protected AppError error;

    public BaseDomainResponse() {
        this.error = null;
    }

    public BaseDomainResponse(AppError error) {
        this.error = error;
    }

    public boolean hasError() {
        return error != null;
    }

    public AppError getError() {
        return error;
    }

    public void setError(AppError error) {
        this.error = error;
    }
}
