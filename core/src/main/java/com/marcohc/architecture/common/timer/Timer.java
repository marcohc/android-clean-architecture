package com.marcohc.architecture.common.timer;

import timber.log.Timber;

/**
 * Used track time spent on a single or multiple lines of code.
 *
 * @author Marco Hernaiz
 * @since 09/08/16
 */
public class Timer {

    private long mStepTime;
    private long mTotalTime;
    private int mStepNumber;
    private String mTag;

    public Timer(String tag) {
        this.mTag = tag;
        mStepNumber = 1;
        mStepTime = System.currentTimeMillis();
        mTotalTime = System.currentTimeMillis();
    }

    /**
     * Prints a log with the step time.
     *
     * @return the step time
     */
    public Long logStep() {
        Long difference = System.currentTimeMillis() - mStepTime;
        Timber.d("%s - Step %d: %d", mTag, mStepNumber++, difference);
        mStepTime = System.currentTimeMillis();
        return difference;
    }

    /**
     * Prints a log with the total time.
     *
     * @return the total time
     */
    public Long logTotal() {
        Long difference = System.currentTimeMillis() - mTotalTime;
        Timber.d("%s - TOTAL: %d", mTag, difference);
        return difference;
    }
}
