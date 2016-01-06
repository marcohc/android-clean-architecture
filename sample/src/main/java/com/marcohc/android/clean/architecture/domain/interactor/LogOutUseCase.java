package com.marcohc.android.clean.architecture.domain.interactor;

import com.marcohc.android.clean.architecture.data.cache.impl.UserCache;
import com.marcohc.android.clean.architecture.domain.bus.request.LogOutRequest;
import com.marcohc.android.clean.architecture.domain.bus.response.data.LogOutDataResponse;
import com.marcohc.android.clean.architecture.domain.bus.response.domain.LogOutDomainResponse;

public class LogOutUseCase extends AsynchronousUseCase {

    // ************************************************************************************************************************************************************************
    // * Bus events factory methods
    // ************************************************************************************************************************************************************************

    @Override
    protected LogOutRequest createRequest() {
        return new LogOutRequest();
    }

    @Override
    protected LogOutDomainResponse createResponse() {
        return new LogOutDomainResponse();
    }

    // ************************************************************************************************************************************************************************
    // * Use case execution
    // ************************************************************************************************************************************************************************

    public void onEventAsync(LogOutDataResponse event) {
        UserCache.getInstance().removeCurrentUser();
        post(createResponse());
        unregisterFromBus();
    }
}
