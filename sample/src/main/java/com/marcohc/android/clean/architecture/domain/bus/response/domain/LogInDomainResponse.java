package com.marcohc.android.clean.architecture.domain.bus.response.domain;

import com.marcohc.android.clean.architecture.common.bus.events.response.domain.BaseDomainResponse;
import com.marcohc.android.clean.architecture.domain.model.UserModel;

public class LogInDomainResponse extends BaseDomainResponse {

    private final UserModel userModel;

    public LogInDomainResponse(UserModel userModel) {
        this.userModel = userModel;
    }

    public UserModel getUser() {
        return userModel;
    }
}
