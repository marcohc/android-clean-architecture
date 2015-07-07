package com.marcohc.android.clean.architecture.domain.bus.event.response;

import com.marcohc.android.clean.architecture.domain.bus.event.BaseEvent;
import com.marcohc.android.clean.architecture.domain.model.UserModel;

public class LogInEventResponse extends BaseEvent {

    private final UserModel userModel;

    public LogInEventResponse(UserModel userModel) {
        this.userModel = userModel;
    }

    public UserModel getUser() {
        return userModel;
    }
}
