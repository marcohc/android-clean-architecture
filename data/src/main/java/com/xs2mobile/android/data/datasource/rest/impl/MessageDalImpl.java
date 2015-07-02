package com.xs2mobile.buurapp.data.datasource.rest.impl;

import com.xs2mobile.buurapp.data.datasource.inter.MessageDal;
import com.xs2mobile.buurapp.data.datasource.rest.RepositoryCallback;
import com.xs2mobile.buurapp.data.datasource.rest.ServiceGenerator;
import com.xs2mobile.buurapp.data.datasource.rest.inter.MessageApiService;
import com.xs2mobile.buurapp.data.entity.inter.MessageEntity;
import com.xs2mobile.buurapp.data.util.NetworkManager;

import java.util.HashMap;

public class MessageDalImpl implements MessageDal {

    @Override
    public void get(Long chatId, Long lastMessageId, String token, RepositoryCallback<Object> callback) {
        MessageApiService service = ServiceGenerator.createService(MessageApiService.class, NetworkManager.BASE_API_URL, token);
        service.get(chatId, lastMessageId, callback);
    }

    @Override
    public void create(MessageEntity message, String token, RepositoryCallback callback) {
        MessageApiService service = ServiceGenerator.createService(MessageApiService.class, NetworkManager.BASE_API_URL, token);
        service.create(message, callback);
    }

    @Override
    public void registerToPushNotifications(HashMap<String, String> map, String token, RepositoryCallback callback) {
        MessageApiService service = ServiceGenerator.createService(MessageApiService.class, NetworkManager.BASE_API_URL, token);
        service.registerToPushNotifications(map, callback);
    }

    @Override
    public void unregisterFromPushNotifications(HashMap<String, Object> map, String token, RepositoryCallback callback) {
        MessageApiService service = ServiceGenerator.createService(MessageApiService.class, NetworkManager.BASE_API_URL, token);
        service.unregisterFromPushNotifications(map, callback);
    }
}
