package com.marcohc.architecture.common.thread;

import android.support.annotation.NonNull;

import com.marcohc.architecture.common.util.utils.Preconditions;

import java.util.concurrent.ThreadFactory;

/**
 * Thread factory which assignees tags for each thread.
 */
class JobThreadFactory implements ThreadFactory {

    private static final String THREAD_NAME = "job_executor_";
    private int counter = 0;

    @Override
    public Thread newThread(@NonNull Runnable runnable) {
        Preconditions.checkNotNull(runnable, "runnable");
        return new Thread(runnable, THREAD_NAME + counter++);
    }
}