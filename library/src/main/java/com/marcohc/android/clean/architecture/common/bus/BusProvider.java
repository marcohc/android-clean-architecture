package com.marcohc.android.clean.architecture.common.bus;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.EventBusException;

import timber.log.Timber;

public final class BusProvider {

    private static final EventBus BUS = EventBus.getDefault();

    private static EventBus getInstance() {
        return BUS;
    }

    public static void register(Object subscriber) {
        try {
            getInstance().register(subscriber);
        } catch (EventBusException e) {
            Timber.i("register: %s", e.getMessage());
            getInstance().unregister(subscriber);
            getInstance().register(subscriber);
        }
    }

    public static void unregister(Object subscriber) {
        getInstance().unregister(subscriber);
    }

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

    private static void log(Object event) {
        String name = event.getClass().getSimpleName();
        if (name.contains("Request")) {
            Timber.v("Request: %s", name);
        } else if (name.contains("DataResponse")) {
            Timber.v("DataResponse: %s", name);
        } else if (name.contains("DomainResponse")) {
            Timber.v("DomainResponse: %s", name);
        } else {
            Timber.v("Posting to bus: %s", name);
        }
    }
}