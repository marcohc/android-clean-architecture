package com.marcohc.android.clean.architecture.domain.bus.event.response;

import com.marcohc.android.clean.architecture.domain.bus.event.BaseEvent;

public class IsFirstTimeInTheAppResponse extends BaseEvent {

    private boolean firstTimeInTheApp;

    public IsFirstTimeInTheAppResponse(boolean firstTimeInApp) {
        this.firstTimeInTheApp = firstTimeInApp;
    }

    public boolean isFirstTimeInTheApp() {
        return firstTimeInTheApp;
    }
}
