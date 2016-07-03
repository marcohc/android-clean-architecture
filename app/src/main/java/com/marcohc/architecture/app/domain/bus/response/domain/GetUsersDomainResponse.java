package com.marcohc.architecture.app.domain.bus.response.domain;

import com.marcohc.architecture.app.domain.model.UserModel;
import com.marcohc.architecture.common.bus.BusEvent;

import java.util.List;

public class GetUsersDomainResponse implements BusEvent {

    private final List<UserModel> usersList;

    public GetUsersDomainResponse(List<UserModel> usersList) {
        this.usersList = usersList;
    }

    public List<UserModel> getUsersList() {
        return usersList;
    }

}
