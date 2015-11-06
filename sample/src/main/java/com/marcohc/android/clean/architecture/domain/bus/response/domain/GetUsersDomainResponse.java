package com.marcohc.android.clean.architecture.domain.bus.response.domain;

import com.marcohc.android.clean.architecture.common.bus.response.domain.BaseDomainResponse;
import com.marcohc.android.clean.architecture.domain.model.UserModel;

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
