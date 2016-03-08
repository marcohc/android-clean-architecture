
package com.marcohc.architecture.app.data.service;

import com.marcohc.architecture.data.net.RestCallback;

import retrofit.http.GET;

public interface UserRestService {

    @GET("/?results=25")
    void getAll(RestCallback callback);

}
