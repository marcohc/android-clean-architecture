package com.marcohc.android.clean.architecture.domain.bus.event.response;

import com.marcohc.android.clean.architecture.domain.bus.event.BaseEvent;

public class IsUserLoggedInResponse extends BaseEvent {

    private Boolean isUserLoggedIn;

    public IsUserLoggedInResponse(boolean isUserLoggedIn) {
        this.isUserLoggedIn = isUserLoggedIn;
    }

    public Boolean isUserLoggedIn() {
        return isUserLoggedIn;
    }

    public void setUserLoggedIn(Boolean userLoggedIn) {
        this.isUserLoggedIn = userLoggedIn;
    }
}
