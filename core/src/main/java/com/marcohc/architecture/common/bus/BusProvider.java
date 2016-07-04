package com.marcohc.architecture.common.bus;

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

    public static void post(BusEvent busEvent) {
        if (busEvent != null) {
            log(busEvent);
            getInstance().post(busEvent);
        }
    }

    public static void cancelEventDelivery(BusEvent busEvent) {
        getInstance().cancelEventDelivery(busEvent);
    }

    public static void postSticky(BusEvent busEvent) {
        if (busEvent != null) {
            log(busEvent);
            getInstance().postSticky(busEvent);
        }
    }

    public static <T> boolean containsStickyEvent(Class<T> clazz) {
        return getInstance().getStickyEvent(clazz) != null;
    }

    public static <T> T getStickyEvent(Class<T> busEventClass) {
        return getInstance().getStickyEvent(busEventClass);
    }

    public static void removeStickyEvent(BusEvent busEvent) {
        getInstance().removeStickyEvent(busEvent);
    }

    public static void removeAllStickyEvents() {
        getInstance().removeAllStickyEvents();
    }

    private static void log(BusEvent busEvent) {
        String name = busEvent.getClass().getSimpleName();
        if (name.contains("Event")) {
            Timber.v("Event: %s", name);
        } else if (name.contains("Request")) {
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