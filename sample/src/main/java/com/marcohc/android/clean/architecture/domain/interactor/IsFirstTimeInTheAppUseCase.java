package com.marcohc.android.clean.architecture.domain.interactor;

import com.marcohc.android.clean.architecture.domain.bus.request.IsFirstTimeInTheAppRequest;
import com.marcohc.android.clean.architecture.domain.bus.response.data.IsFirstTimeInTheAppDataResponse;

public class IsFirstTimeInTheAppUseCase extends SynchronousUseCase {

    // ************************************************************************************************************************************************************************
    // * Attributes
    // ************************************************************************************************************************************************************************

    private IsFirstTimeInTheAppRequest request;
    private IsFirstTimeInTheAppDataResponse response;

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

    public void onEvent(IsFirstTimeInTheAppDataResponse event) {
        this.response = event;
    }

}
