package com.marcohc.architecture.app.domain.interactor;

import com.marcohc.architecture.app.domain.bus.request.GetUsersRequest;
import com.marcohc.architecture.app.domain.bus.response.data.GetUsersDataResponse;
import com.marcohc.architecture.app.domain.bus.response.domain.GetUsersDomainResponse;
import com.marcohc.architecture.domain.error.DomainError;
import com.marcohc.architecture.domain.interactor.AsynchronousUseCase;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class GetUsersUseCase extends AsynchronousUseCase {

    // ************************************************************************************************************************************************************************
    // * Attributes
    // ************************************************************************************************************************************************************************

    private GetUsersDataResponse responseFromServer;

    // ************************************************************************************************************************************************************************
    // * Bus events factory methods
    // ************************************************************************************************************************************************************************

    @Override
    protected GetUsersRequest createRequest() {
        return new GetUsersRequest();
    }

    @Override
    protected GetUsersDomainResponse createResponse() {
        return new GetUsersDomainResponse(responseFromServer.getResponse());
    }

    // ************************************************************************************************************************************************************************
    // * Use case execution
    // ************************************************************************************************************************************************************************

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onEventAsync(GetUsersDataResponse responseFromServer) {
        this.responseFromServer = responseFromServer;
        if (!responseFromServer.hasError()) {
            post(createResponse());
            unregisterFromBus();
        } else {
            post(new DomainError(responseFromServer.getError().getMessage(), responseFromServer.getError().getCode()));
        }
    }
}
