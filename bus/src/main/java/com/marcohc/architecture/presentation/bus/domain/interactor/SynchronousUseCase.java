package com.marcohc.architecture.presentation.bus.domain.interactor;

/**
 * This class represents an synchronous use case
 * Used for simple business logic
 */
public abstract class SynchronousUseCase<T> implements BusUseCase {

    /**
     * Executes the logic of the use case
     *
     * @return the result of your use case
     */
    public abstract T execute();

}
