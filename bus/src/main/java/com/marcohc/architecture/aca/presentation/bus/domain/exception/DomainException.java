package com.marcohc.architecture.aca.presentation.bus.domain.exception;

/**
 * An error which occurs on the domain layer.
 *
 * @author Marco Hernaiz
 * @since 09/08/16
 */
public class DomainException extends Exception {

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
