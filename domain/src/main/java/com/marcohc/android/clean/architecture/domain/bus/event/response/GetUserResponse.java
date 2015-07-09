package com.marcohc.android.clean.architecture.domain.bus.event.response;

import com.marcohc.android.clean.architecture.domain.bus.event.BaseEvent;
import com.marcohc.android.clean.architecture.domain.model.UserModel;

public class GetUserResponse extends BaseEvent {

    private final UserModel user;

    public GetUserResponse(UserModel user) {
        this.user = user;
    }

    public UserModel getUser() {
        return user;
    }
}
