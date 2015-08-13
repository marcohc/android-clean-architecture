package com.marcohc.android.clean.architecture.domain.interactor.impl;

import com.marcohc.android.clean.architecture.domain.bus.event.request.IsUserLoggedInRequest;
import com.marcohc.android.clean.architecture.domain.bus.event.response.IsUserLoggedInResponse;
import com.marcohc.android.clean.architecture.domain.interactor.inter.SynchronousUseCase;

public class IsFirstTimeInTheAppUseCase extends SynchronousUseCase {

    // ************************************************************************************************************************************************************************
    // * Attributes
    // ************************************************************************************************************************************************************************

    private IsUserLoggedInRequest request;
    private IsUserLoggedInResponse response;

    // ************************************************************************************************************************************************************************
    // * Bus events factory methods
    // ************************************************************************************************************************************************************************

    @Override
    protected IsUserLoggedInRequest createRequest() {
        return new IsUserLoggedInRequest();
    }

    // ************************************************************************************************************************************************************************
    // * Use case execution
    // ************************************************************************************************************************************************************************

    @Override
    public Boolean execute() {
        request = createRequest();
        post(request);
        assert response != null;
        return response.isUserLoggedIn();
    }

    // ************************************************************************************************************************************************************************
    // * Bus event event handlers
    // ************************************************************************************************************************************************************************

    public void onEvent(IsUserLoggedInResponse event) {
        this.response = event;
    }
}
