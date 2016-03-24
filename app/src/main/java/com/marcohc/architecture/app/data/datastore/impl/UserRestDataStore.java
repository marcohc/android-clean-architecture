package com.marcohc.architecture.app.data.datastore.impl;

import com.marcohc.architecture.app.data.datastore.inter.UserDataStore;
import com.marcohc.architecture.app.data.service.UserRestService;
import com.marcohc.architecture.app.data.util.NetworkManager;
import com.marcohc.architecture.app.domain.model.UserModel;
import com.marcohc.architecture.data.net.DataCallback;
import com.marcohc.architecture.data.net.ServiceGenerator;

import java.util.List;

import retrofit2.converter.jackson.JacksonConverterFactory;

public class UserRestDataStore implements UserDataStore {

    @Override
    public void getAll(DataCallback<List<UserModel>> callback) {
        UserRestService userRestService = ServiceGenerator.getInstance().createService(UserRestService.class, JacksonConverterFactory.create(), NetworkManager.BASE_API_URL);
        userRestService.getAll().enqueue(callback);
    }

}
