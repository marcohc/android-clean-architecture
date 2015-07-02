package com.xs2mobile.buurapp.data.datasource.rest.impl;

import com.xs2mobile.buurapp.data.datasource.inter.UserDal;
import com.xs2mobile.buurapp.data.datasource.rest.RepositoryCallback;
import com.xs2mobile.buurapp.data.datasource.rest.ServiceGenerator;
import com.xs2mobile.buurapp.data.datasource.rest.inter.UserApiService;
import com.xs2mobile.buurapp.data.entity.inter.UserEntity;
import com.xs2mobile.buurapp.data.util.NetworkManager;

public class UserDalImpl implements UserDal {

    @Override
    public void getNeighbours(String token, RepositoryCallback<Object> callback) {
        UserApiService userApiService = ServiceGenerator.createService(UserApiService.class, NetworkManager.BASE_API_URL, token);
        userApiService.getNeighbours(callback);
    }

    @Override
    public void getUserDetails(Long userId, String token, RepositoryCallback callback) {
        UserApiService userApiService = ServiceGenerator.createService(UserApiService.class, NetworkManager.BASE_API_URL, token);
        userApiService.getUserDetails(userId, callback);
    }

    @Override
    public void update(UserEntity user, String token, RepositoryCallback callback) {
        UserApiService userApiService = ServiceGenerator.createService(UserApiService.class, NetworkManager.BASE_API_URL, token);
        userApiService.update(user, callback);
    }

    @Override
    public void register(UserEntity user, RepositoryCallback callback) {
        UserApiService userApiService = ServiceGenerator.createService(UserApiService.class, NetworkManager.BASE_API_URL);
        userApiService.register(user, callback);
    }

    @Override
    public void logIn(String username, String password, RepositoryCallback callback) {
        UserApiService userApiService = ServiceGenerator.createService(UserApiService.class, NetworkManager.BASE_API_URL, username, password);
        userApiService.logIn(callback);
    }

}
