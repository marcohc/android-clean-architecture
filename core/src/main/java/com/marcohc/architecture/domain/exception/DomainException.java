package com.marcohc.architecture.domain.exception;

/**
 * An error which occurs on the domain layer.
 *
 * @author Marco Hernaiz
 * @since 09/08/16
 */
public class DomainException extends Throwable {

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
