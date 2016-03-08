package com.marcohc.architecture.app.data.datastore.impl;

import com.marcohc.architecture.app.data.datastore.inter.UserDataStore;
import com.marcohc.architecture.app.data.net.RestCallback;
import com.marcohc.architecture.app.data.net.ServiceGenerator;
import com.marcohc.architecture.app.data.service.UserRestService;
import com.marcohc.architecture.app.data.util.NetworkManager;

public class UserRestDataStore implements UserDataStore {

    @Override
    public void getAll(RestCallback callback) {
        UserRestService userRestService = ServiceGenerator.createService(UserRestService.class, NetworkManager.BASE_API_URL);
        userRestService.getAll(callback);
    }

}
