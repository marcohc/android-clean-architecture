package com.marcohc.android.clean.architecture.domain.interactor.impl;

import com.marcohc.android.clean.architecture.common.exception.DataException;
import com.marcohc.android.clean.architecture.domain.bus.event.request.LogInRequest;
import com.marcohc.android.clean.architecture.domain.bus.event.request.SaveUserRequest;
import com.marcohc.android.clean.architecture.domain.bus.event.response.LogInEventResponse;
import com.marcohc.android.clean.architecture.domain.bus.event.response.LogInResponse;
import com.marcohc.android.clean.architecture.domain.interactor.inter.AsynchronousUseCase;
import com.marcohc.android.clean.architecture.domain.mapper.UserMapper;
import com.marcohc.android.clean.architecture.domain.model.UserModel;

public class LogInUseCase extends AsynchronousUseCase {

    // ************************************************************************************************************************************************************************
    // * Attributes
    // ************************************************************************************************************************************************************************

    private final String username;
    private final String password;
    private LogInResponse response;

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

    @Override
    public void execute() {
        getBus().post(createRequest());
    }

    // ************************************************************************************************************************************************************************
    // * Use case execution
    // ************************************************************************************************************************************************************************

    public void onEventAsync(LogInResponse event) {
        this.response = event;
        DataException error = DataException.getError(event.getResponse());
        if (error == null) {
            LogInEventResponse response = createResponse();
            post(new SaveUserRequest(response.getUser()));
            post(response);
            unregisterFromBus();
        } else {
            handleException(error);
        }
    }
}
