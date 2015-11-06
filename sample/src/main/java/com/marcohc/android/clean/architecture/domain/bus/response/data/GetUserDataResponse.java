package com.marcohc.android.clean.architecture.domain.bus.response.data;

import com.marcohc.android.clean.architecture.common.bus.response.data.BaseDataResponse;
import com.marcohc.android.clean.architecture.domain.entity.UserEntity;

public class GetUserDataResponse extends BaseDataResponse {

    private final UserEntity user;

    public GetUserDataResponse(UserEntity user) {
        this.user = user;
    }

    public UserEntity getUser() {
        return user;
    }
}
