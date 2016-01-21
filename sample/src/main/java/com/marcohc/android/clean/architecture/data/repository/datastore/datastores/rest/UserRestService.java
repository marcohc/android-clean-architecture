
package com.marcohc.android.clean.architecture.data.repository.datastore.datastores.rest;

import com.marcohc.android.clean.architecture.data.net.RestCallback;

import retrofit.http.GET;
import retrofit.http.POST;

public interface UserRestService {

    @POST("/login")
    void logIn(RestCallback callback);

    @GET("/?results=25")
    void getAll(RestCallback callback);

}
