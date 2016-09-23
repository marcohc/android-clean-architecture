package com.marcohc.architecture.app.domain.exception;

import com.marcohc.architecture.domain.exception.DomainException;
import com.marcohc.architecture.rx.domain.interactor.RxUseCase;

/**
 * Sample exception which happens in the Domain layer, to understand the approach of exception catching in the {@link RxUseCase}.
 *
 * @author Marco Hernaiz
 * @since 08/08/16
 */
public class RandomUserDomainException extends DomainException {

    public RandomUserDomainException(String message) {
        super(message);
    }

}
