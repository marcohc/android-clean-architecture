package com.marcohc.android.clean.architecture.domain.interactor;

import com.marcohc.android.clean.architecture.domain.bus.request.IsUserLoggedInRequest;
import com.marcohc.android.clean.architecture.domain.bus.response.data.IsUserLoggedInDataResponse;

public class IsUserLoggedInUseCase extends SynchronousUseCase {

    // ************************************************************************************************************************************************************************
    // * Attributes
    // ************************************************************************************************************************************************************************

    private IsUserLoggedInRequest request;
    private IsUserLoggedInDataResponse response;

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

    public void onEvent(IsUserLoggedInDataResponse event) {
        this.response = event;
    }
}
