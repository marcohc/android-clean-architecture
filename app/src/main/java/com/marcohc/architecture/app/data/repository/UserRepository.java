package com.marcohc.architecture.app.data.repository;

import com.marcohc.architecture.app.data.factory.UserDataStoreFactory;
import com.marcohc.architecture.app.domain.bus.request.GetUsersRequest;
import com.marcohc.architecture.app.domain.bus.response.data.GetUsersDataResponse;
import com.marcohc.architecture.app.domain.model.UserModel;
import com.marcohc.architecture.common.bus.BusHandler;
import com.marcohc.architecture.data.error.DataException;
import com.marcohc.architecture.data.error.RestError;
import com.marcohc.architecture.data.net.DataCallback;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/**
 * All the data management must be here. Nothing related to business logic
 */
public class UserRepository extends BusHandler {

    // ************************************************************************************************************************************************************************
    // * Attributes
    // ************************************************************************************************************************************************************************

    private static UserRepository repository;

    // ************************************************************************************************************************************************************************
    // * Initialization methods
    // ************************************************************************************************************************************************************************

    /**
     * This method must be called when starting the app to connect to the EventBus
     */
    public static void setUp() {
        if (repository == null) {
            repository = new UserRepository();
        }
    }

    // ************************************************************************************************************************************************************************
    // * Event handler methods
    // ************************************************************************************************************************************************************************

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onGetUsersRequest(GetUsersRequest request) {
        UserDataStoreFactory.getInstance().getAll(new DataCallback<List<UserModel>>() {
            @Override
            public void onFailure(RestError error) {
                post(new DataException(error.getMessage(), error.getCode()));
            }

            @Override
            public void onSuccess(List<UserModel> response) {
                post(new GetUsersDataResponse(response));
            }
        });
    }
}
