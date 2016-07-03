package com.marcohc.architecture.domain.error;

import com.marcohc.architecture.common.bus.BusEvent;

/**
 * An error which occurs on the domain layer
 */
public class DomainException extends Exception implements BusEvent {

    public DomainException() {
    }

    public DomainException(String detailMessage) {
        super(detailMessage);
    }

    public DomainException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public DomainException(Throwable throwable) {
        super(throwable);
    }

}
