package com.marcohc.architecture.rx.domain.executor;

import com.marcohc.architecture.domain.executor.PriorityJobExecutor;
import com.marcohc.architecture.rx.domain.interactor.RxUseCase;
import com.marcohc.architecture.rx.domain.interactor.SimpleSubscriber;

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
    public void executeUseCase(final RxUseCase useCase, final SimpleSubscriber subscriber) {
        useCase.execute(subscriber, Schedulers.from(threadPoolExecutor), AndroidSchedulers.mainThread());
    }

}
