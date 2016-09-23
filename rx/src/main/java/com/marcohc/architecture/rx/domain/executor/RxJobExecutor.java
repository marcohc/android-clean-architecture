package com.marcohc.architecture.rx.domain.executor;

import com.marcohc.architecture.domain.executor.PriorityJobExecutor;
import com.marcohc.architecture.rx.domain.interactor.BaseSubscriber;
import com.marcohc.architecture.rx.domain.interactor.RxUseCase;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Executor with a thread pool.
 *
 * @author Marco Hernaiz
 * @since 09/08/16
 */
public final class RxJobExecutor extends PriorityJobExecutor {

    private static RxJobExecutor instance;

    private RxJobExecutor() {
        super();
    }

    public synchronized static RxJobExecutor getInstance() {
        if (instance == null) {
            instance = new RxJobExecutor();
        }
        return instance;
    }

    /**
     * Executes a high priority use case in a thread of the pool and returns the result in the MainThread.
     *
     * @param useCase    the use case to execute
     * @param subscriber the subscriber where you want to get the response
     */
    @SuppressWarnings("unchecked")
    public void executeHighPriorityUseCase(final RxUseCase useCase, final BaseSubscriber subscriber) {
        useCase.execute(subscriber, Schedulers.from(highPriorityThreadPoolExecutor), AndroidSchedulers.mainThread());
//        executeHighPriorityRunnable(new PriorityRunnable(Priority.IMMEDIATE) {
//            @Override
//            public void run() {
//
//            }
//        });
    }

    /**
     * Executes a low priority use case in a thread of the pool and returns the result in the MainThread.
     *
     * @param useCase    the use case to execute
     * @param subscriber the subscriber where you want to get the response
     */
    @SuppressWarnings("unchecked")
    public void executeLowPriorityUseCase(final RxUseCase useCase, final BaseSubscriber subscriber) {
        useCase.execute(subscriber, Schedulers.from(lowPriorityThreadPoolExecutor), AndroidSchedulers.mainThread());
//        executeLowPriorityRunnable(new PriorityRunnable(Priority.LOW) {
//            @Override
//            public void run() {
//
//            }
//        });
    }
}
