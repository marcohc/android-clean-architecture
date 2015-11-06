package com.marcohc.android.clean.architecture.domain.bus.request;

import com.marcohc.android.clean.architecture.common.bus.event.BaseEvent;
import com.marcohc.android.clean.architecture.domain.entity.UserEntity;

public class SaveUserRequest extends BaseEvent {

    private final UserEntity user;

    public SaveUserRequest(UserEntity user) {
        this.user = user;
    }

    public UserEntity getUser() {
        return user;
    }
}
