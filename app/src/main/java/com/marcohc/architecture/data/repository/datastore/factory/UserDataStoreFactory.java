package com.marcohc.architecture.data.repository.datastore.factory;

import com.marcohc.architecture.data.repository.datastore.UserDataStore;
import com.marcohc.architecture.data.repository.datastore.datastores.disk.UserDiskDataSource;
import com.marcohc.architecture.data.repository.datastore.datastores.rest.UserRestDataStore;
import com.marcohc.architecture.data.net.RestCallback;

import org.json.JSONObject;

/**
 * This class applies the logic of selecting which data source
 * use for each situation and the use of the cache
 */
public class UserDataStoreFactory {

    // ************************************************************************************************************************************************************************
    // * Attributes
    // ************************************************************************************************************************************************************************

    private static UserDataStoreFactory instance;
    private final UserDataStore userCloudDataStore;
    private final UserDataStore userDiskDataSource;

    // ************************************************************************************************************************************************************************
    // * Initialization methods
    // ************************************************************************************************************************************************************************

    public UserDataStoreFactory() {
        userCloudDataStore = new UserRestDataStore();
        userDiskDataSource = new UserDiskDataSource();
    }

    public static UserDataStoreFactory getInstance() {
        if (instance == null) {
            instance = new UserDataStoreFactory();
        }
        return instance;
    }

    // ************************************************************************************************************************************************************************
    // * Initialization methods
    // ************************************************************************************************************************************************************************

    public void logIn(String username, String password, RestCallback<JSONObject> callback) {
        userCloudDataStore.logIn(username, password, callback);
    }

    public void signUp(String username, String password, RestCallback<JSONObject> callback) {
        userCloudDataStore.signUp(username, password, callback);
    }

    public void getAll(RestCallback<JSONObject> callback) {
        userCloudDataStore.getAll(callback);
    }

//    public void logOut() {
//        userDiskDataSource.delete();
//    }
//
//    public void put(UserEntity user) {
//        userDiskDataSource.put(user);
//    }

}
