package com.marcohc.android.clean.architecture.domain.interactor;

import com.marcohc.android.clean.architecture.common.bus.response.data.BaseDataResponse;
import com.marcohc.android.clean.architecture.domain.bus.request.LogOutRequest;

public class LogOutUseCase extends SynchronousUseCase {

    private BaseDataResponse response;

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

    public void onEvent(BaseDataResponse event) {
        this.response = event;
    }
}
