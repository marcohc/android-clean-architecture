package com.marcohc.architecture.data.datastore.impl;

import com.marcohc.architecture.data.net.RestCallback;
import com.marcohc.architecture.data.datastore.inter.UserDataStore;
import com.marcohc.architecture.data.datastore.inter.UserRestService;
import com.marcohc.architecture.data.datastore.util.ServiceGenerator;
import com.marcohc.architecture.data.util.NetworkManager;

public class UserRestDataStore implements UserDataStore {

    @Override
    public void getAll(RestCallback callback) {
        UserRestService userRestService = ServiceGenerator.createService(UserRestService.class, NetworkManager.BASE_API_URL);
        userRestService.getAll(callback);
    }

}
