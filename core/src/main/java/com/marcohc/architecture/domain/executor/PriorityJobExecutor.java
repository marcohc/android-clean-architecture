package com.marcohc.architecture.domain.executor;

import android.support.annotation.NonNull;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Priority job executor with a two thread pool.
 *
 * @author Marco Hernaiz
 * @since 09/08/16
 */
public class PriorityJobExecutor {

    // ************************************************************************************************************************************************************************
    // * Attributes
    // ************************************************************************************************************************************************************************

    private static final int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();
    private static final int CORE_POOL_SIZE = NUMBER_OF_CORES * 2;
    private static final int MAX_POOL_SIZE = Integer.MAX_VALUE;
    private static final int KEEP_ALIVE_TIME = 10;
    private static final TimeUnit KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS;

    // ************************************************************************************************************************************************************************
    // * Constants
    // ************************************************************************************************************************************************************************

    protected final ThreadPoolExecutor threadPoolExecutor;
    protected final PriorityThreadPoolExecutor highPriorityThreadPoolExecutor;
    protected final PriorityThreadPoolExecutor lowPriorityThreadPoolExecutor;

    // ************************************************************************************************************************************************************************
    // * Initialization methods
    // ************************************************************************************************************************************************************************

    protected PriorityJobExecutor() {
        ThreadFactory threadFactory = new JobThreadFactory();
        BlockingQueue<Runnable> linkedBlockingQueue = new LinkedBlockingQueue<>();
        this.threadPoolExecutor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME, KEEP_ALIVE_TIME_UNIT, linkedBlockingQueue, threadFactory);
        this.highPriorityThreadPoolExecutor = new PriorityThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME, KEEP_ALIVE_TIME_UNIT, threadFactory);
        this.lowPriorityThreadPoolExecutor = new PriorityThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME, KEEP_ALIVE_TIME_UNIT, threadFactory);
    }

    /**
     * Thread factory which assignees tags for each thread.
     */
    private static class JobThreadFactory implements ThreadFactory {
        private static final String THREAD_NAME = "job_executor_";
        private int counter = 0;

        @Override
        public Thread newThread(@NonNull Runnable runnable) {
            return new Thread(runnable, THREAD_NAME + counter++);
        }
    }

    // ************************************************************************************************************************************************************************
    // * Execution methods
    // ************************************************************************************************************************************************************************

    protected void executeRunnable(Runnable runnable) {
        threadPoolExecutor.execute(runnable);
    }

    protected void executeHighPriorityRunnable(PriorityRunnable priorityRunnable) {
        highPriorityThreadPoolExecutor.submit(priorityRunnable);
    }

    protected void executeLowPriorityRunnable(PriorityRunnable priorityRunnable) {
        lowPriorityThreadPoolExecutor.submit(priorityRunnable);
    }

}
