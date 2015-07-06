package com.marcohc.android.clean.architecture.data.datasource.impl.local;

import com.marcohc.android.clean.architecture.common.exception.DataException;
import com.marcohc.android.clean.architecture.data.datasource.impl.rest.ServiceGenerator;
import com.marcohc.android.clean.architecture.data.datasource.impl.rest.inter.MessageApiService;
import com.marcohc.android.clean.architecture.data.datasource.inter.MessageDataSource;
import com.marcohc.android.clean.architecture.data.net.RepositoryCallback;
import com.marcohc.android.clean.architecture.data.util.NetworkManager;
import com.marcohc.android.clean.architecture.domain.entity.MessageEntity;

import java.util.HashMap;

public class MessageLocalDataSource implements MessageDataSource {

    @Override
    public void get(Long chatId, Long lastMessageId, String token) {
        MessageApiService service = ServiceGenerator.createService(MessageApiService.class, NetworkManager.BASE_API_URL, token);
        service.get(chatId, lastMessageId, new RepositoryCallback<Object>() {
            @Override
            public void failure(DataException error) {

            }

            @Override
            public void success(Object response) {

            }
        });
    }

    @Override
    public void create(MessageEntity message, String token) {
    }

    @Override
    public void registerToPushNotifications(HashMap<String, String> map, String token) {
    }

    @Override
    public void unregisterFromPushNotifications(HashMap<String, Object> map, String token) {
    }

}
