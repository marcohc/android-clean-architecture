package com.marcohc.android.clean.architecture.common.bus;

import de.greenrobot.event.EventBus;

public final class BusProvider {

    private static final EventBus BUS = EventBus.getDefault();

    public static EventBus getInstance() {
        return BUS;
    }

    private BusProvider() {
        // No instances.
    }
}