
package com.marcohc.android.clean.architecture.data.datasource.impl.rest.inter;

import com.marcohc.android.clean.architecture.data.net.RepositoryCallback;

import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

public interface UserApiService {

    @POST("/login")
    void logIn(RepositoryCallback callback);

    @GET("/people/details/{userId}/")
    void getUserDetails(@Path("userId") Long userId, RepositoryCallback callback);

}
