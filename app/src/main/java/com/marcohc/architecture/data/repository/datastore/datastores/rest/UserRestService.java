
package com.marcohc.architecture.data.repository.datastore.datastores.rest;

import com.marcohc.architecture.data.net.RestCallback;

import retrofit.http.GET;

public interface UserRestService {

    @GET("/?results=25")
    void getAll(RestCallback callback);

}
