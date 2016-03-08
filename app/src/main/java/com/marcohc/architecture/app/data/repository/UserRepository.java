package com.marcohc.architecture.app.data.repository;

import com.marcohc.architecture.common.bus.BusHandler;
import com.marcohc.architecture.data.error.DataError;
import com.marcohc.architecture.data.error.RestError;
import com.marcohc.architecture.data.net.RestCallback;
import com.marcohc.architecture.app.data.factory.UserDataStoreFactory;
import com.marcohc.architecture.app.domain.bus.request.GetUsersRequest;
import com.marcohc.architecture.app.domain.bus.response.data.GetUsersDataResponse;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONObject;

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
    public void onEventAsync(GetUsersRequest request) {
        UserDataStoreFactory.getInstance().getAll(new RestCallback<JSONObject>() {
            @Override
            public void failure(RestError error) {
                postDataError(new DataError(error.getMessage(), error.getCode()));
            }

            @Override
            public void success(JSONObject response) {
                post(new GetUsersDataResponse(response));
            }
        });
    }
}
