package com.marcohc.android.clean.architecture.domain.bus.event.request;

import com.marcohc.android.clean.architecture.domain.bus.event.BaseEvent;

public class IsUserLoggedInRequest extends BaseEvent {

    private Boolean isUserLoggedIn;

    public Boolean isUserLoggedIn() {
        return isUserLoggedIn;
    }

    public void setUserLoggedIn(Boolean userLoggedIn) {
        this.isUserLoggedIn = userLoggedIn;
    }
}
