package com.marcohc.android.clean.architecture.domain.bus.event.response;

import com.marcohc.android.clean.architecture.domain.bus.event.BaseEvent;
import com.marcohc.android.clean.architecture.domain.entity.inter.UserEntity;

public class GetUserResponse extends BaseEvent {

    private final UserEntity user;

    public GetUserResponse(UserEntity user) {
        this.user = user;
    }

    public UserEntity getUser() {
        return user;
    }
}
