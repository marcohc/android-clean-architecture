package com.marcohc.android.clean.architecture.domain.bus.event.response;

import com.marcohc.android.clean.architecture.domain.bus.event.BaseEvent;
import com.marcohc.android.clean.architecture.domain.model.UserModel;

import java.util.List;

public class GetUsersResponse extends BaseEvent {


    private final List<UserModel> usersList;

    public GetUsersResponse(List<UserModel> usersList) {

        this.usersList = usersList;
    }

    public List<UserModel> getUsersList() {
        return usersList;
    }
}
