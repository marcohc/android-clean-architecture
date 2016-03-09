package com.marcohc.architecture.common.helper;

import timber.log.Timber;

/**
 * Used track time spent on a single or multiple lines of code
 */
public class TimerLog {

    private long timer;
    private int stepNumber;
    private String tag;

    public TimerLog(String tag) {
        this.tag = tag;
    }

    public void start() {
        stepNumber = 0;
        Timber.d("%d - %s - Time: %d", stepNumber++, tag, 0);
        timer = System.currentTimeMillis();
    }

    public Long log() {
        Long difference = System.currentTimeMillis() - timer;
        Timber.d("%d - %s - Time: %d", stepNumber++, tag, difference);
        timer = System.currentTimeMillis();
        return difference;
    }
}
