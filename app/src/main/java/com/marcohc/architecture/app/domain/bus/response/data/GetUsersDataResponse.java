package com.marcohc.architecture.app.domain.bus.response.data;

import com.marcohc.architecture.app.domain.model.UserModel;
import com.marcohc.architecture.common.bus.events.response.data.BaseDataResponse;

import java.util.List;

public class GetUsersDataResponse extends BaseDataResponse {

    private final List<UserModel> response;

    public GetUsersDataResponse(List<UserModel> response) {
        this.response = response;
    }

    public List<UserModel> getResponse() {
        return response;
    }
}
