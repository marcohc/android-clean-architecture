package com.xs2mobile.buurapp.data.datasource.rest.impl;

import com.xs2mobile.buurapp.data.datasource.inter.ChatDal;
import com.xs2mobile.buurapp.data.datasource.rest.RepositoryCallback;
import com.xs2mobile.buurapp.data.datasource.rest.ServiceGenerator;
import com.xs2mobile.buurapp.data.datasource.rest.inter.ChatApiService;
import com.xs2mobile.buurapp.data.util.NetworkManager;

import java.util.HashMap;

public class ChatDalImpl implements ChatDal {

    @Override
    public void getChats(String token, RepositoryCallback<Object> callback) {
        ChatApiService service = ServiceGenerator.createService(ChatApiService.class, NetworkManager.BASE_API_URL, token);
        service.getChats(callback);
    }

    @Override
    public void createChat(HashMap<String, String> map, String token, RepositoryCallback callback) {
        ChatApiService service = ServiceGenerator.createService(ChatApiService.class, NetworkManager.BASE_API_URL, token);
        service.createChat(map, callback);
    }
}
