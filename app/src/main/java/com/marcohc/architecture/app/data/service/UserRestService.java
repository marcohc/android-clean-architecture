
package com.marcohc.architecture.app.data.service;

import com.marcohc.architecture.app.domain.model.UserModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UserRestService {

    @GET("/users")
    Call<List<UserModel>> getAll();

}
