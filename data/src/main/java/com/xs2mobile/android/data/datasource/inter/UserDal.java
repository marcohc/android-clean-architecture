package com.xs2mobile.buurapp.data.datasource.inter;

import com.xs2mobile.buurapp.data.entity.inter.UserEntity;
import com.xs2mobile.buurapp.data.datasource.rest.RepositoryCallback;

public interface UserDal {

    void register(UserEntity user, RepositoryCallback callback);

    void logIn(String username, String password, RepositoryCallback callback);

    void getNeighbours(String token, RepositoryCallback<Object> callback);

    void update(UserEntity user, String token, RepositoryCallback callback);

    void getUserDetails(Long userId, String token, RepositoryCallback callback);
}
