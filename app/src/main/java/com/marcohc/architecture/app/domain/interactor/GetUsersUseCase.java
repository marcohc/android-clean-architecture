package com.marcohc.architecture.app.domain.interactor;

import com.marcohc.architecture.app.domain.bus.request.GetUsersRequest;
import com.marcohc.architecture.app.domain.bus.response.data.GetUsersDataResponse;
import com.marcohc.architecture.app.domain.bus.response.domain.GetUsersDomainResponse;
import com.marcohc.architecture.app.domain.mapper.UserMapper;
import com.marcohc.architecture.app.domain.model.UserModel;
import com.marcohc.architecture.domain.interactor.AsynchronousUseCase;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

public class GetUsersUseCase extends AsynchronousUseCase {

    // ************************************************************************************************************************************************************************
    // * Attributes
    // ************************************************************************************************************************************************************************

    private List<UserModel> modelList;

    // ************************************************************************************************************************************************************************
    // * Bus events factory methods
    // ************************************************************************************************************************************************************************

    @Override
    protected GetUsersRequest createRequest() {
        return new GetUsersRequest();
    }

    @Override
    protected GetUsersDomainResponse createResponse() {
        return new GetUsersDomainResponse(modelList);
    }


    // ************************************************************************************************************************************************************************
    // * Use case execution
    // ************************************************************************************************************************************************************************

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onGetUsersDataResponse(GetUsersDataResponse responseFromServer) {
        modelList = UserMapper.getInstance().parseEntityList(responseFromServer.getEntityList());
        post(createResponse());
        unregisterFromBus();
    }
}
