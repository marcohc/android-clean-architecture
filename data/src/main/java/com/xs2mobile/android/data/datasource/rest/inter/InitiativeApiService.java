
package com.xs2mobile.buurapp.data.datasource.rest.inter;

import com.xs2mobile.buurapp.data.datasource.rest.RepositoryCallback;
import com.xs2mobile.buurapp.data.entity.inter.InitiativeEntity;

import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;

public interface InitiativeApiService {

    @GET("/initiative/{id}")
    void get(@Path("id") Long id, RepositoryCallback<Object> callback);

    @GET("/initiatives/")
    void get(RepositoryCallback callback);

    @POST("/initiative/")
    void create(@Body InitiativeEntity initiative, RepositoryCallback callback);

    @PUT("/initiative/{id}")
    void update(@Path("id") Long id, @Body InitiativeEntity initiative, RepositoryCallback callback);

    @DELETE("/initiative/{id}")
    void delete(@Path("id") Long id, RepositoryCallback callback);

}
