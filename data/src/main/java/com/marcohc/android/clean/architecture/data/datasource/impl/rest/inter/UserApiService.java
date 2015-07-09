
package com.marcohc.android.clean.architecture.data.datasource.impl.rest.inter;

import com.marcohc.android.clean.architecture.data.net.RepositoryCallback;

import retrofit.http.GET;
import retrofit.http.POST;

public interface UserApiService {

    @POST("/login")
    void logIn(RepositoryCallback callback);

    @GET("/?results=25")
    void get(RepositoryCallback callback);

}
