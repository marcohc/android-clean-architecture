package com.xs2mobile.buurapp.data.datasource.rest.impl;

import com.xs2mobile.buurapp.data.datasource.inter.InitiativeDal;
import com.xs2mobile.buurapp.data.entity.inter.InitiativeEntity;
import com.xs2mobile.buurapp.data.datasource.rest.RepositoryCallback;
import com.xs2mobile.buurapp.data.datasource.rest.ServiceGenerator;
import com.xs2mobile.buurapp.data.datasource.rest.inter.InitiativeApiService;
import com.xs2mobile.buurapp.data.util.NetworkManager;

import org.json.JSONObject;

public class InitiativeDalImpl implements InitiativeDal {

    @Override
    public void get(String token, RepositoryCallback<Object> callback) {
        InitiativeApiService service = ServiceGenerator.createService(InitiativeApiService.class, NetworkManager.BASE_API_URL, token);
        service.get(callback);
    }

    @Override
    public void get(Long initiativeId, String token, RepositoryCallback callback) {
        InitiativeApiService service = ServiceGenerator.createService(InitiativeApiService.class, NetworkManager.BASE_API_URL, token);
        service.get(initiativeId, callback);
    }

    @Override
    public void create(InitiativeEntity initiative, String token, RepositoryCallback callback) {
        InitiativeApiService service = ServiceGenerator.createService(InitiativeApiService.class, NetworkManager.BASE_API_URL, token);
        service.create(initiative, callback);
    }

    @Override
    public void update(InitiativeEntity initiative, String token, RepositoryCallback callback) {
        InitiativeApiService service = ServiceGenerator.createService(InitiativeApiService.class, NetworkManager.BASE_API_URL, token);
        service.update(initiative.getId(), initiative, callback);
    }

    @Override
    public void delete(Long id, String token, RepositoryCallback<JSONObject> callback) {
        InitiativeApiService service = ServiceGenerator.createService(InitiativeApiService.class, NetworkManager.BASE_API_URL, token);
        service.delete(id, callback);
    }
}
