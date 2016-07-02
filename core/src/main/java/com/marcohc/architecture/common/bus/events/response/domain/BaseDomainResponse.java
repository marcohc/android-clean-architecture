package com.marcohc.architecture.common.bus.events.response.domain;

import com.marcohc.architecture.common.bus.events.BusEvent;
import com.marcohc.architecture.domain.error.DomainException;

/**
 * Common methods for domain responses
 */
public abstract class BaseDomainResponse implements BusEvent {

    protected DomainException error;

    public BaseDomainResponse() {
        this.error = null;
    }

    public BaseDomainResponse(DomainException error) {
        this.error = error;
    }

    public boolean hasError() {
        return error != null;
    }

    public DomainException getError() {
        return error;
    }

    public void setError(DomainException error) {
        this.error = error;
    }
}
