package com.marcohc.architecture.domain.bus.response.domain;

import com.marcohc.architecture.common.bus.events.response.domain.BaseDomainResponse;
import com.marcohc.architecture.domain.model.UserModel;

import java.util.List;

public class GetUsersDomainResponse extends BaseDomainResponse {

    private final List<UserModel> usersList;

    public GetUsersDomainResponse(List<UserModel> usersList) {
        this.usersList = usersList;
    }

    public List<UserModel> getUsersList() {
        return usersList;
    }

}
