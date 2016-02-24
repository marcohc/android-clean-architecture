package com.marcohc.architecture.domain.interactor;

/**
 * This class represents an synchronous use case not connected to the bus.
 * Used for simple business logic
 */
public abstract class SynchronousUseCase implements UseCase {

    public abstract Object execute();

}
