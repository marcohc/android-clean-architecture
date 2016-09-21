package com.marcohc.architecture.data.executor;

import android.support.annotation.NonNull;

import com.marcohc.architecture.domain.interactor.BaseSubscriber;
import com.marcohc.architecture.domain.interactor.UseCase;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Executor with a thread pool.
 *
 * @author Marco Hernaiz
 * @since 09/08/16
 */
public final class JobExecutor implements Executor {

    // ************************************************************************************************************************************************************************
    // * Constants
    // ************************************************************************************************************************************************************************

    private static final int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();
    private static final int CORE_POOL_SIZE = NUMBER_OF_CORES * 2;
    private static final int MAX_POOL_SIZE = Integer.MAX_VALUE;
    private static final int KEEP_ALIVE_TIME = 10;
    private static final TimeUnit KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS;

    // ************************************************************************************************************************************************************************
    // * Attributes
    // ************************************************************************************************************************************************************************

    private static JobExecutor sInstance;
    private final BlockingQueue<Runnable> mWorkQueue;
    private final ThreadPoolExecutor mThreadPoolExecutor;
    private final ThreadFactory mThreadFactory;

    // ************************************************************************************************************************************************************************
    // * Constructor
    // ************************************************************************************************************************************************************************

    private JobExecutor() {
        this.mWorkQueue = new LinkedBlockingQueue<>();
        this.mThreadFactory = new JobThreadFactory();
        this.mThreadPoolExecutor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME, KEEP_ALIVE_TIME_UNIT, this.mWorkQueue, this.mThreadFactory);
    }

    /**
     * Get singleton instance for this class.
     *
     * @return the singleton instance
     */
    public synchronized static JobExecutor getInstance() {
        if (sInstance == null) {
            sInstance = new JobExecutor();
        }
        return sInstance;
    }

    // ************************************************************************************************************************************************************************
    // * Constructor
    // ************************************************************************************************************************************************************************

    @Override
    public void execute(@NonNull Runnable runnable) {
        mThreadPoolExecutor.execute(runnable);
    }

    /**
     * Executes a use case in a thread of the pool and returns the result in the MainThread.
     *
     * @param useCase    the use case to execute
     * @param subscriber the subscriber where you want to get the response
     */
    @SuppressWarnings("unchecked")
    public void executeUseCase(UseCase useCase, BaseSubscriber subscriber) {
        useCase.execute(subscriber, Schedulers.from(mThreadPoolExecutor), AndroidSchedulers.mainThread());
    }

    // ************************************************************************************************************************************************************************
    // * Factory
    // ************************************************************************************************************************************************************************

    /**
     * Thread factory which assignees tags for each thread.
     */
    private static class JobThreadFactory implements ThreadFactory {
        private static final String THREAD_NAME = "android_";
        private int mCounter = 0;

        @Override
        public Thread newThread(@NonNull Runnable runnable) {
            return new Thread(runnable, THREAD_NAME + mCounter++);
        }
    }
}
