package com.marcohc.architecture.common.helper;

import timber.log.Timber;

/**
 * Used track time spent on a single or multiple lines of code
 */
public class TimerLog {

    private long setTimer;
    private long totalTimer;
    private int stepNumber;
    private String tag;

    public static TimerLog getInstance(String tag) {
        TimerLog timerLog = new TimerLog();
        timerLog.tag = tag;
        timerLog.stepNumber = 1;
        timerLog.setTimer = System.currentTimeMillis();
        timerLog.totalTimer = System.currentTimeMillis();
        return timerLog;
    }

    public Long logStep() {
        Long difference = System.currentTimeMillis() - setTimer;
        Timber.d("%s - Step %d: %d", tag, stepNumber++, difference);
        setTimer = System.currentTimeMillis();
        return difference;
    }

    public Long logTotal() {
        Long difference = System.currentTimeMillis() - totalTimer;
        Timber.d("%s - TOTAL: %d", tag, difference);
        return difference;
    }
}
