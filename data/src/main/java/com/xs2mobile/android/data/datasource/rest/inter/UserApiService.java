
package com.xs2mobile.buurapp.data.datasource.rest.inter;

import com.xs2mobile.buurapp.data.entity.inter.UserEntity;
import com.xs2mobile.buurapp.data.datasource.rest.RepositoryCallback;

import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

public interface UserApiService {

    @POST("/people/register/")
    void register(@Body UserEntity user, RepositoryCallback callback);

    @POST("/login")
    void logIn(RepositoryCallback callback);

    @POST("/people/update/")
    void update(@Body UserEntity user, RepositoryCallback callback);

    @GET("/people/details/{userId}/")
    void getUserDetails(@Path("userId") Long userId, RepositoryCallback callback);

    @GET("/people/neighbours/")
    void getNeighbours(RepositoryCallback callback);

}
