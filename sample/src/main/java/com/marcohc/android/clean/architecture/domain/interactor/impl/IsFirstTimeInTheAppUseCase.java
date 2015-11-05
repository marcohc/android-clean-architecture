package com.marcohc.android.clean.architecture.domain.interactor.impl;

import com.marcohc.android.clean.architecture.domain.bus.event.request.IsFirstTimeInTheAppRequest;
import com.marcohc.android.clean.architecture.domain.bus.event.response.IsFirstTimeInTheAppResponse;
import com.marcohc.android.clean.architecture.domain.interactor.inter.SynchronousUseCase;

public class IsFirstTimeInTheAppUseCase extends SynchronousUseCase {

    // ************************************************************************************************************************************************************************
    // * Attributes
    // ************************************************************************************************************************************************************************

    private IsFirstTimeInTheAppRequest request;
    private IsFirstTimeInTheAppResponse response;

    // ************************************************************************************************************************************************************************
    // * Bus events factory methods
    // ************************************************************************************************************************************************************************

    @Override
    protected IsFirstTimeInTheAppRequest createRequest() {
        return new IsFirstTimeInTheAppRequest();
    }

    // ************************************************************************************************************************************************************************
    // * Use case execution
    // ************************************************************************************************************************************************************************

    @Override
    public Boolean execute() {
        request = createRequest();
        post(request);
        assert response != null;
        return response.isFirstTimeInTheApp();
    }

    // ************************************************************************************************************************************************************************
    // * Bus event event handlers
    // ************************************************************************************************************************************************************************

    public void onEvent(IsFirstTimeInTheAppResponse event) {
        this.response = event;
    }

}
