package com.marcohc.android.clean.architecture.domain.bus.event.request;

import com.marcohc.android.clean.architecture.domain.bus.event.BaseEvent;
import com.marcohc.android.clean.architecture.domain.model.UserModel;

public class SaveUserRequest extends BaseEvent {

    private final UserModel user;

    public SaveUserRequest(UserModel user) {
        this.user = user;
    }

    public UserModel getUser() {
        return user;
    }
}
