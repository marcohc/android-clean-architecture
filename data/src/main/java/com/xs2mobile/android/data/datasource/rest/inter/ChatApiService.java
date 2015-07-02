
package com.xs2mobile.buurapp.data.datasource.rest.inter;

import com.xs2mobile.buurapp.data.datasource.rest.RepositoryCallback;

import java.util.HashMap;

import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;

public interface ChatApiService {

    @GET("/chat/")
    void getChats(RepositoryCallback<Object> callback);

    @POST("/chat/")
    void createChat(@Body HashMap<String, String> map, RepositoryCallback callback);

}
