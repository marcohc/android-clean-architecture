package com.marcohc.android.clean.architecture.domain.interactor.impl;

import com.marcohc.android.clean.architecture.domain.bus.event.request.LogOutRequest;
import com.marcohc.android.clean.architecture.domain.bus.event.response.BaseResponse;
import com.marcohc.android.clean.architecture.domain.interactor.inter.SynchronousUseCase;

public class LogOutUseCase extends SynchronousUseCase {

    private BaseResponse response;

    // ************************************************************************************************************************************************************************
    // * Bus events factory methods
    // ************************************************************************************************************************************************************************

    @Override
    protected LogOutRequest createRequest() {
        return new LogOutRequest();
    }

    // ************************************************************************************************************************************************************************
    // * Use case execution
    // ************************************************************************************************************************************************************************

    @Override
    public Boolean execute() {
        post(createRequest());
        return !response.hasError();
    }

    // ************************************************************************************************************************************************************************
    // * Bus event event handlers
    // ************************************************************************************************************************************************************************

    public void onEvent(BaseResponse event) {
        this.response = event;
    }
}
