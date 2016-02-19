package com.marcohc.android.clean.architecture.domain.interactor;

import com.marcohc.android.clean.architecture.domain.bus.request.LogInRequest;
import com.marcohc.android.clean.architecture.domain.bus.response.data.LogInDataResponse;
import com.marcohc.android.clean.architecture.domain.bus.response.domain.LogInDomainResponse;
import com.marcohc.android.clean.architecture.domain.error.DomainError;
import com.marcohc.android.clean.architecture.domain.mapper.UserMapper;
import com.marcohc.android.clean.architecture.domain.model.UserModel;
import com.marcohc.android.clean.architecture.domain.util.AuthenticationManager;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class LogInUseCase extends AsynchronousUseCase {

    // ************************************************************************************************************************************************************************
    // * Attributes
    // ************************************************************************************************************************************************************************

    private final String username;
    private final String password;
    private LogInDataResponse response;
    private UserModel userModel;

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
    protected LogInDomainResponse createResponse() {
        return new LogInDomainResponse(userModel);
    }

    // ************************************************************************************************************************************************************************
    // * Use case execution
    // ************************************************************************************************************************************************************************

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onEventAsync(LogInDataResponse dataResponse) {

        this.response = dataResponse;
        if (!dataResponse.hasError()) {

            userModel = UserMapper.getInstance().parseModel(response.getResponse().toString());

            // Save current user
            userModel.setKey(userModel.getEmail());
            AuthenticationManager.getInstance().logIn(userModel.getKey(), userModel);

            post(createResponse());
            unregisterFromBus();

        } else {
            postAppError(new DomainError(dataResponse.getError().getMessage(), dataResponse.getError().getCode()));
            unregisterFromBus();
        }
    }
}
