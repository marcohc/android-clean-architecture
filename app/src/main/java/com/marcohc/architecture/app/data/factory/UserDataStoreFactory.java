package com.marcohc.architecture.app.data.factory;

import com.marcohc.architecture.data.net.RestCallback;
import com.marcohc.architecture.app.data.datastore.inter.UserDataStore;
import com.marcohc.architecture.app.data.datastore.impl.UserRestDataStore;

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

    // ************************************************************************************************************************************************************************
    // * Initialization methods
    // ************************************************************************************************************************************************************************

    public UserDataStoreFactory() {
        userCloudDataStore = new UserRestDataStore();
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

    public void getAll(RestCallback<JSONObject> callback) {
        userCloudDataStore.getAll(callback);
    }

}