package com.marcohc.android.clean.architecture.domain.interactor.impl;

import com.marcohc.android.clean.architecture.domain.bus.event.request.LogInRequest;
import com.marcohc.android.clean.architecture.domain.bus.event.request.SaveUserRequest;
import com.marcohc.android.clean.architecture.domain.bus.event.response.BaseResponse;
import com.marcohc.android.clean.architecture.domain.bus.event.response.LogInEventResponse;
import com.marcohc.android.clean.architecture.domain.interactor.inter.AsynchronousUseCase;
import com.marcohc.android.clean.architecture.domain.mapper.UserMapper;
import com.marcohc.android.clean.architecture.domain.model.UserModel;

public class LogInUseCase extends AsynchronousUseCase {

    // ************************************************************************************************************************************************************************
    // * Attributes
    // ************************************************************************************************************************************************************************

    private final String username;
    private final String password;
    private BaseResponse response;

    // ************************************************************************************************************************************************************************
    // * Constructor
    // ************************************************************************************************************************************************************************

    public LogInUseCase(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }

    // ************************************************************************************************************************************************************************
    // * Bus events factory methods
    // ************************************************************************************************************************************************************************

    @Override
    protected LogInRequest createRequest() {
        return new LogInRequest(username, password);
    }

    @Override
    protected LogInEventResponse createResponse() {
        UserModel userModel = UserMapper.parseUser(response.getResponse());
        return new LogInEventResponse(userModel);
    }

    // ************************************************************************************************************************************************************************
    // * Use case execution
    // ************************************************************************************************************************************************************************

    public void onEventAsync(BaseResponse event) {

        this.response = event;
        if (!event.hasError()) {
            LogInEventResponse response = createResponse();

            // Save current user
            post(new SaveUserRequest(UserMapper.transform(response.getUser())));

            // Return the current user
            post(response);
            unregisterFromBus();
        } else {
            handleException(event.getError());
        }
    }
}
