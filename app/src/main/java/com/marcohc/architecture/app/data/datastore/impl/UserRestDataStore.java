package com.marcohc.architecture.app.data.datastore.impl;

import com.marcohc.architecture.app.data.datastore.inter.UserDataStore;
import com.marcohc.architecture.app.data.service.UserRestService;
import com.marcohc.architecture.app.data.util.NetworkManager;
import com.marcohc.architecture.app.domain.entity.UserEntity;
import com.marcohc.architecture.data.net.DataCallback;
import com.marcohc.architecture.data.net.ServiceGenerator;

import java.util.List;

import retrofit2.converter.gson.GsonConverterFactory;

public class UserRestDataStore implements UserDataStore {

    @Override
    public void getAll(DataCallback<List<UserEntity>> callback) {
        UserRestService userRestService = ServiceGenerator.getInstance().createService(UserRestService.class, GsonConverterFactory.create(), NetworkManager.BASE_API_URL);
        userRestService.getAll().enqueue(callback);
    }

}
