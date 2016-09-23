package com.marcohc.architecture.rx.data.exception;

import com.marcohc.architecture.domain.exception.DomainException;

/**
 * This exception will be thrown automatically if the use case doesn't handle the exception given in the data layer properly.
 *
 * @author Marco Hernaiz
 * @since 09/08/16
 */
public class NotHandledDataException extends DomainException {

    public NotHandledDataException(Throwable throwable) {
        super(throwable);
    }
}
