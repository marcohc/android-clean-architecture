package com.marcohc.android.clean.architecture.data.repository;

import com.marcohc.android.clean.architecture.common.exception.DataException;
import com.marcohc.android.clean.architecture.data.repository.datastore.factory.UserDataStoreFactory;
import com.marcohc.android.clean.architecture.data.repository.net.RepositoryCallback;
import com.marcohc.android.clean.architecture.domain.bus.event.request.GetUserRequest;
import com.marcohc.android.clean.architecture.domain.bus.event.request.GetUsersRequest;
import com.marcohc.android.clean.architecture.domain.bus.event.request.IsFirstTimeInTheAppRequest;
import com.marcohc.android.clean.architecture.domain.bus.event.request.IsUserLoggedInRequest;
import com.marcohc.android.clean.architecture.domain.bus.event.request.LogInRequest;
import com.marcohc.android.clean.architecture.domain.bus.event.request.LogOutRequest;
import com.marcohc.android.clean.architecture.domain.bus.event.request.SaveUserRequest;
import com.marcohc.android.clean.architecture.domain.bus.event.response.BaseResponse;
import com.marcohc.android.clean.architecture.domain.bus.event.response.GetUserResponse;
import com.marcohc.android.clean.architecture.domain.bus.event.response.IsUserLoggedInResponse;
import com.marcohc.android.clean.architecture.domain.interactor.inter.BusHandler;

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
    public static void initialize() {
        if (repository == null) {
            repository = new UserRepository();
        }
    }

    // ************************************************************************************************************************************************************************
    // * Synchronous event handler methods
    // ************************************************************************************************************************************************************************

    public void onEvent(IsUserLoggedInRequest request) {
        post(new IsUserLoggedInResponse(UserDataStoreFactory.getInstance().getCurrentUser() != null));
    }

    public void onEvent(IsFirstTimeInTheAppRequest request) {
        post(new IsUserLoggedInResponse(UserDataStoreFactory.getInstance().getCurrentUser() != null));
    }

    public void onEvent(GetUserRequest request) {
        post(new GetUserResponse(UserDataStoreFactory.getInstance().getCurrentUser()));
    }

    public void onEvent(LogOutRequest request) {
        UserDataStoreFactory.getInstance().logOut();
        post(new BaseResponse());
    }

    // ************************************************************************************************************************************************************************
    // * Asynchronous event handler methods
    // ************************************************************************************************************************************************************************

    public void onEventBackgroundThread(LogInRequest request) {
        UserDataStoreFactory.getInstance().logIn(request.getUsername(), request.getPassword(), new RepositoryCallback<JSONObject>() {
            @Override
            public void failure(DataException error) {
                postException(error);
            }

            @Override
            public void success(JSONObject response) {
                post(new BaseResponse(response));
            }
        });
    }

    public void onEventBackgroundThread(GetUsersRequest request) {
        UserDataStoreFactory.getInstance().getAll(new RepositoryCallback<JSONObject>() {
            @Override
            public void failure(DataException error) {
                postException(error);
            }

            @Override
            public void success(JSONObject response) {
                post(new BaseResponse(response));
            }
        });
    }

    public void onEventBackgroundThread(SaveUserRequest request) {
        UserDataStoreFactory.getInstance().put(request.getUser());
    }

}
