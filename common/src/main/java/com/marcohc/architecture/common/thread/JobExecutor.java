package com.marcohc.architecture.common.thread;

import android.support.annotation.NonNull;

import com.marcohc.architecture.common.util.utils.Preconditions;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Executor with a thread pool.
 *
 * @author Marco Hernaiz
 * @since 09/08/16
 */
public final class JobExecutor {

    private static final int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();
    private static final int CORE_POOL_SIZE = NUMBER_OF_CORES * 2;
    private static final int MAX_POOL_SIZE = Integer.MAX_VALUE;
    private static final int KEEP_ALIVE_TIME = 10;
    private static final TimeUnit KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS;
    private static final ThreadPoolExecutor threadPoolExecutor;

    static {
        ThreadFactory threadFactory = new JobThreadFactory();
        BlockingQueue<Runnable> linkedBlockingQueue = new LinkedBlockingQueue<>();
        threadPoolExecutor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME, KEEP_ALIVE_TIME_UNIT, linkedBlockingQueue, threadFactory);
    }

    private JobExecutor() {
    }

    public static void execute(@NonNull Runnable runnable) {
        Preconditions.checkNotNull(runnable, "runnable");
        threadPoolExecutor.execute(runnable);
    }

    public static ThreadPoolExecutor getThreadPoolExecutor() {
        return threadPoolExecutor;
    }

}