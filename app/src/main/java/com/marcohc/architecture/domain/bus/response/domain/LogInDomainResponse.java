package com.marcohc.architecture.domain.bus.response.domain;

import com.marcohc.architecture.common.bus.events.response.domain.BaseDomainResponse;
import com.marcohc.architecture.domain.model.UserModel;

public class LogInDomainResponse extends BaseDomainResponse {

    private final UserModel userModel;

    public LogInDomainResponse(UserModel userModel) {
        this.userModel = userModel;
    }

    public UserModel getUser() {
        return userModel;
    }
}
