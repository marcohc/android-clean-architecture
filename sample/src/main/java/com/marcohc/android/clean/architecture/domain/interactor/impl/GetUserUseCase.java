package com.marcohc.android.clean.architecture.domain.interactor.impl;

import com.marcohc.android.clean.architecture.domain.bus.event.request.GetUserRequest;
import com.marcohc.android.clean.architecture.domain.bus.event.response.GetUserResponse;
import com.marcohc.android.clean.architecture.domain.interactor.inter.SynchronousUseCase;
import com.marcohc.android.clean.architecture.domain.mapper.UserMapper;
import com.marcohc.android.clean.architecture.domain.model.UserModel;

public class GetUserUseCase extends SynchronousUseCase {

    // ************************************************************************************************************************************************************************
    // * Attributes
    // ************************************************************************************************************************************************************************

    private GetUserRequest request;
    private GetUserResponse response;

    // ************************************************************************************************************************************************************************
    // * Bus events factory methods
    // ************************************************************************************************************************************************************************

    @Override
    protected GetUserRequest createRequest() {
        return new GetUserRequest();
    }

    // ************************************************************************************************************************************************************************
    // * Use case execution
    // ************************************************************************************************************************************************************************

    @Override
    public UserModel execute() {
        request = createRequest();
        post(request);
        assert response != null;
        return UserMapper.transform(response.getUser());
    }

    // ************************************************************************************************************************************************************************
    // * Bus event event handlers
    // ************************************************************************************************************************************************************************

    public void onEvent(GetUserResponse event) {
        this.response = event;
    }
}
