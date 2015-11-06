package com.marcohc.android.clean.architecture.domain.interactor;

import com.marcohc.android.clean.architecture.domain.bus.request.GetUserRequest;
import com.marcohc.android.clean.architecture.domain.bus.response.data.GetUserDataResponse;
import com.marcohc.android.clean.architecture.domain.mapper.UserMapper;
import com.marcohc.android.clean.architecture.domain.model.UserModel;

public class GetUserUseCase extends SynchronousUseCase {

    // ************************************************************************************************************************************************************************
    // * Attributes
    // ************************************************************************************************************************************************************************

    private GetUserRequest request;
    private GetUserDataResponse response;

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
        return UserMapper.parse(response.getUser());
    }

    // ************************************************************************************************************************************************************************
    // * Bus event event handlers
    // ************************************************************************************************************************************************************************

    public void onEvent(GetUserDataResponse event) {
        this.response = event;
    }
}
