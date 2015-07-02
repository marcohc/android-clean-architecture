package com.xs2mobile.buurapp.data.datasource.inter;

import com.xs2mobile.buurapp.data.datasource.rest.RepositoryCallback;
import com.xs2mobile.buurapp.data.entity.inter.InitiativeEntity;

import org.json.JSONObject;

public interface InitiativeDal {

    void get(String token, RepositoryCallback<Object> callback);

    void get(Long initiativeId, String token, RepositoryCallback callback);

    void create(InitiativeEntity initiative, String token, RepositoryCallback callback);

    void update(InitiativeEntity initiative, String token, RepositoryCallback<JSONObject> callback);

    void delete(Long id, String token, RepositoryCallback<JSONObject> callback);

}
