package com.marcohc.android.clean.architecture.common.bus.response.domain;

import com.marcohc.android.clean.architecture.common.bus.event.BusEvent;
import com.marcohc.android.clean.architecture.common.exception.DomainError;

/**
 * Common methods for responses
 */
public abstract class BaseDomainResponse implements BusEvent {

    protected DomainError error;

    public BaseDomainResponse() {
        this.error = null;
    }

    public BaseDomainResponse(DomainError error) {
        this.error = error;
    }

    public boolean hasError() {
        return error != null;
    }

    public DomainError getError() {
        return error;
    }

    public void setError(DomainError error) {
        this.error = error;
    }
}
