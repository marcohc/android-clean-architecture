package com.marcohc.architecture.domain.interactor;

/**
 * This class represents an synchronous use case
 * Used for simple business logic
 */
public abstract class SynchronousUseCase<T> implements UseCase {

    /**
     * Executes the logic of the use case
     *
     * @return the result of your use case
     */
    public abstract T execute();

}
