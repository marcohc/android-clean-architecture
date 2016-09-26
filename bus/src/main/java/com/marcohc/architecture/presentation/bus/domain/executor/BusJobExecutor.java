package com.marcohc.architecture.presentation.bus.domain.executor;

import com.marcohc.architecture.domain.executor.Priority;
import com.marcohc.architecture.domain.executor.PriorityJobExecutor;
import com.marcohc.architecture.domain.executor.PriorityRunnable;
import com.marcohc.architecture.presentation.bus.domain.interactor.BusUseCase;

/**
 * Executor with a thread pool.
 *
 * @author Marco Hernaiz
 * @since 09/08/16
 */
public final class BusJobExecutor extends PriorityJobExecutor {

    private static BusJobExecutor instance;

    private BusJobExecutor() {
        super();
    }

    public synchronized static BusJobExecutor getInstance() {
        if (instance == null) {
            instance = new BusJobExecutor();
        }
        return instance;
    }

    /**
     * Executes a high priority use case in a thread of the pool.
     *
     * @param useCase the use case to execute
     */
    public void executeHighPriorityUseCase(final BusUseCase useCase) {
        threadPoolExecutor.submit(new PriorityRunnable(Priority.HIGH) {
            @Override
            public void run() {
                useCase.execute();
            }
        });
    }

    /**
     * Executes a low priority use case in a thread of the pool.
     *
     * @param useCase the use case to execute
     */
    public void executeLowPriorityUseCase(final BusUseCase useCase) {
        lowPriorityThreadPoolExecutor.submit(new PriorityRunnable(Priority.LOW) {
            @Override
            public void run() {
                useCase.execute();
            }
        });
    }

}
