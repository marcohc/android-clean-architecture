package com.marcohc.android.clean.architecture.bus;


import android.util.Log;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.EventBusException;

public final class BusProvider {

    private static final String LOG_TAG = "Event";
    private static final EventBus BUS = EventBus.getDefault();

    public static void post(Object event) {
        if (event != null) {
            log(event);
            getInstance().post(event);
        }
    }

    public static void postSticky(Object event) {
        if (event != null) {
            log(event);
            getInstance().postSticky(event);
        }
    }

    private static EventBus getInstance() {
        return BUS;
    }

    public static void register(Object subscriber) {
        try {
            getInstance().register(subscriber);
        } catch (EventBusException e) {
            Log.i(BusProvider.LOG_TAG, String.format("register: %s", e.getMessage()));
            getInstance().unregister(subscriber);
            getInstance().register(subscriber);
        }
    }

    public static void registerSticky(Object subscriber) {
        try {
            getInstance().registerSticky(subscriber);
        } catch (EventBusException e) {
            Log.i(BusProvider.LOG_TAG, String.format("registerSticky: %s", e.getMessage()));
            getInstance().unregister(subscriber);
            getInstance().registerSticky(subscriber);
        }
    }

    public static void removeStickyEvent(Object event) {
        getInstance().removeStickyEvent(event);
    }

    public static void removeAllStickyEvents() {
        getInstance().removeAllStickyEvents();
    }

    public static void unregister(Object subscriber) {
        getInstance().unregister(subscriber);
    }

    private static void log(Object event) {
        String name = event.getClass().getSimpleName();
        if (name.contains("Request")) {
            Log.v(BusProvider.LOG_TAG, String.format("Request: %s", name));
        } else if (name.contains("DataResponse")) {
            Log.v(BusProvider.LOG_TAG, String.format("DataResponse: %s", name));
        } else if (name.contains("DomainResponse")) {
            Log.v(BusProvider.LOG_TAG, String.format("DomainResponse: %s", name));
        } else {
            Log.v(BusProvider.LOG_TAG, String.format("Posting to bus: %s", name));
        }
    }
}