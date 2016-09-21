package com.marcohc.architecture.app.domain.exception;

import com.marcohc.architecture.domain.exception.DomainException;

/**
 * Sample exception which happens in the Domain layer, to understand the approach of exception catching in the {@link com.marcohc.architecture.domain.interactor.UseCase}.
 *
 * @author Marco Hernaiz
 * @since 08/08/16
 */
public class RandomUserDomainException extends DomainException {

    public RandomUserDomainException(String message) {
        super(message);
    }

}
