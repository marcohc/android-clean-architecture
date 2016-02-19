package com.marcohc.android.clean.architecture.data.repository;

import com.marcohc.android.clean.architecture.common.bus.BusHandler;
import com.marcohc.android.clean.architecture.data.error.DataError;
import com.marcohc.android.clean.architecture.data.error.RestError;
import com.marcohc.android.clean.architecture.data.net.RestCallback;
import com.marcohc.android.clean.architecture.data.repository.datastore.factory.UserDataStoreFactory;
import com.marcohc.android.clean.architecture.domain.bus.request.GetUsersRequest;
import com.marcohc.android.clean.architecture.domain.bus.request.LogInRequest;
import com.marcohc.android.clean.architecture.domain.bus.request.SignUpRequest;
import com.marcohc.android.clean.architecture.domain.bus.response.data.GetUsersDataResponse;
import com.marcohc.android.clean.architecture.domain.bus.response.data.LogInDataResponse;
import com.marcohc.android.clean.architecture.domain.bus.response.data.SignUpDataResponse;

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
    public void onEventAsync(LogInRequest request) {
        UserDataStoreFactory.getInstance().logIn(request.getUsername(), request.getPassword(), new RestCallback<JSONObject>() {
            @Override
            public void failure(RestError error) {
                postDataError(new DataError(error.getMessage(), error.getCode()));
            }

            @Override
            public void success(JSONObject response) {
                post(new LogInDataResponse(response));
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onEventAsync(SignUpRequest request) {
        UserDataStoreFactory.getInstance().signUp(request.getUsername(), request.getPassword(),
                new RestCallback<JSONObject>() {
                    @Override
                    public void failure(RestError error) {
                        postDataError(new DataError(error.getMessage(), error.getCode()));
                    }

                    @Override
                    public void success(JSONObject response) {
                        post(new SignUpDataResponse(response));
                    }
                });
    }

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
