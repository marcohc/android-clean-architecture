package com.marcohc.architecture.common.util;

import timber.log.Timber;

/**
 * Used track time spent on a single or multiple lines of code.
 *
 * @author Marco Hernaiz
 * @since 09/08/16
 */
public class TimerUtils {

    private long stepTime;
    private long totalTime;
    private int stepNumber;
    private String tag;

    public static TimerUtils getInstance(String tag) {
        return new TimerUtils(tag);
    }

    private TimerUtils(String tag) {
        this.tag = tag;
        stepNumber = 1;
        stepTime = System.currentTimeMillis();
        totalTime = System.currentTimeMillis();
    }

    /**
     * Prints a log with the step time.
     *
     * @return the step time
     */
    public Long logStep() {
        Long difference = System.currentTimeMillis() - stepTime;
        Timber.d("%s - Step %d: %d", tag, stepNumber++, difference);
        stepTime = System.currentTimeMillis();
        return difference;
    }

    /**
     * Prints a log with the step time.
     *
     * @param subTag sub tag used in the log
     * @return the step time
     */
    public Long logStep(String subTag) {
        Long difference = System.currentTimeMillis() - stepTime;
        Timber.d("%s (%s) - Step %d: %d", android.R.attr.tag, subTag, stepNumber++, difference);
        stepTime = System.currentTimeMillis();
        return difference;
    }

    /**
     * Prints a log with the total time.
     *
     * @return the total time
     */
    public Long logTotal() {
        Long difference = System.currentTimeMillis() - totalTime;
        Timber.d("%s - TOTAL: %d", tag, difference);
        return difference;
    }
}
