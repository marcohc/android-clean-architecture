package com.marcohc.android.clean.architecture.domain.interactor;

/**
 * This class represents an synchronous use case.
 */
public abstract class SynchronousUseCase implements UseCase {

    public abstract Object execute();

}
