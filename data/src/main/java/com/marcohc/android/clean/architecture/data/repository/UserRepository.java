package com.marcohc.android.clean.architecture.data.repository;

import com.marcohc.android.clean.architecture.common.exception.DataException;
import com.marcohc.android.clean.architecture.data.datasource.impl.rest.impl.UserRemoteDataSource;
import com.marcohc.android.clean.architecture.data.datasource.inter.UserDataSource;
import com.marcohc.android.clean.architecture.data.net.RepositoryCallback;
import com.marcohc.android.clean.architecture.data.util.NetworkManager;
import com.marcohc.android.clean.architecture.data.util.PreferencesManager;
import com.marcohc.android.clean.architecture.domain.bus.event.request.IsUserLoggedInRequest;
import com.marcohc.android.clean.architecture.domain.bus.event.request.LogInRequest;
import com.marcohc.android.clean.architecture.domain.bus.event.request.SaveUserRequest;
import com.marcohc.android.clean.architecture.domain.bus.event.response.IsUserLoggedInResponse;
import com.marcohc.android.clean.architecture.domain.bus.event.response.LogInResponse;
import com.marcohc.android.clean.architecture.domain.interactor.inter.BusHandler;
import com.marcohc.android.clean.architecture.domain.model.UserModel;
import com.marcohc.helperoid.ParserHelper;

import org.json.JSONObject;

// TODO: Implement UserCache
public class UserRepository extends BusHandler {

    // ************************************************************************************************************************************************************************
    // * Attributes
    // ************************************************************************************************************************************************************************

    private static UserDataSource userDataSource;
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
            }
        }
    }

    // ************************************************************************************************************************************************************************
    // * Bus handler methods data
    // ************************************************************************************************************************************************************************

    public void onEvent(IsUserLoggedInRequest request) {
        post(new IsUserLoggedInResponse(getCurrentUser() != null));
    }

    public void onEventAsync(LogInRequest request) {
        userDataSource.logIn(request.getUsername(), request.getPassword(), new RepositoryCallback<JSONObject>() {
            @Override
            public void failure(DataException error) {
                postException(error);
            }

            @Override
            public void success(JSONObject response) {
                post(new LogInResponse(response));
            }
        });
    }

    public void onEventBackgroundThread(SaveUserRequest request) {
        PreferencesManager.saveUser(ParserHelper.toJsonString(request.getUser()));
    }

    // ************************************************************************************************************************************************************************
    // * Local data
    // ************************************************************************************************************************************************************************

    public void saveUser(UserModel userModel) {

    }

    public UserModel getCurrentUser() {
        return ParserHelper.parseJson(PreferencesManager.getUser(), UserModel.class);
    }
}
