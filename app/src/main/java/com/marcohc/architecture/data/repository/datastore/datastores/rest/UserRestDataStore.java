package com.marcohc.architecture.data.repository.datastore.datastores.rest;

import com.marcohc.architecture.data.net.RestCallback;
import com.marcohc.architecture.data.repository.datastore.UserDataStore;
import com.marcohc.architecture.data.repository.datastore.datastores.rest.util.ServiceGenerator;
import com.marcohc.architecture.data.util.NetworkManager;

public class UserRestDataStore implements UserDataStore {

    @Override
    public void getAll(RestCallback callback) {
        UserRestService userRestService = ServiceGenerator.createService(UserRestService.class, NetworkManager.BASE_API_URL);
        userRestService.getAll(callback);
    }

}
