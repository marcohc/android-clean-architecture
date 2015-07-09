package com.marcohc.android.clean.architecture.data.repository;

import com.marcohc.android.clean.architecture.common.exception.DataException;
import com.marcohc.android.clean.architecture.data.cache.impl.UserCacheImpl;
import com.marcohc.android.clean.architecture.data.cache.inter.UserCache;
import com.marcohc.android.clean.architecture.data.datasource.impl.rest.impl.UserRemoteDataSource;
import com.marcohc.android.clean.architecture.data.datasource.inter.UserDataSource;
import com.marcohc.android.clean.architecture.data.net.RepositoryCallback;
import com.marcohc.android.clean.architecture.data.util.NetworkManager;
import com.marcohc.android.clean.architecture.domain.bus.event.request.GetUserRequest;
import com.marcohc.android.clean.architecture.domain.bus.event.request.GetUsersRequest;
import com.marcohc.android.clean.architecture.domain.bus.event.request.IsUserLoggedInRequest;
import com.marcohc.android.clean.architecture.domain.bus.event.request.LogInRequest;
import com.marcohc.android.clean.architecture.domain.bus.event.request.LogOutRequest;
import com.marcohc.android.clean.architecture.domain.bus.event.request.SaveUserRequest;
import com.marcohc.android.clean.architecture.domain.bus.event.response.BaseResponse;
import com.marcohc.android.clean.architecture.domain.bus.event.response.GetUserResponse;
import com.marcohc.android.clean.architecture.domain.bus.event.response.IsUserLoggedInResponse;
import com.marcohc.android.clean.architecture.domain.interactor.inter.BusHandler;

import org.json.JSONObject;

// TODO: Implement UserCache
public class UserRepository extends BusHandler {

    // ************************************************************************************************************************************************************************
    // * Attributes
    // ************************************************************************************************************************************************************************

    private static UserDataSource userDataSource;
    private static UserCache userCache;
    private static UserRepository repository;

    // ************************************************************************************************************************************************************************
    // * Initialization methods
    // ************************************************************************************************************************************************************************

    public static void initialize() {
        if (repository == null) {
            repository = new UserRepository();
            if (NetworkManager.PERSISTENCE_MANAGER.equals(NetworkManager.LOCAL_PERSISTENCE)) {
                throw new ExceptionInInitializerError("Local persistence it's not developed yet!");
            } else if (NetworkManager.PERSISTENCE_MANAGER.equals(NetworkManager.REMOTE_PERSISTENCE)) {
                userDataSource = new UserRemoteDataSource();
                userCache = new UserCacheImpl();
            }
        }
    }

    // ************************************************************************************************************************************************************************
    // * Synchronous event handler methods
    // ************************************************************************************************************************************************************************

    public void onEvent(IsUserLoggedInRequest request) {
        post(new IsUserLoggedInResponse(userCache.get() != null));
    }

    public void onEvent(GetUserRequest request) {
        post(new GetUserResponse(userCache.get()));
    }

    public void onEvent(LogOutRequest request) {
        userCache.remove();
        post(new BaseResponse());
    }

    // ************************************************************************************************************************************************************************
    // * Asynchronous event handler methods
    // ************************************************************************************************************************************************************************

    public void onEventBackgroundThread(LogInRequest request) {
        userDataSource.logIn(request.getUsername(), request.getPassword(), new RepositoryCallback<JSONObject>() {
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
        userDataSource.get(new RepositoryCallback<JSONObject>() {
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
        userCache.put(request.getUser());
    }
}
