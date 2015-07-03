package com.marcohc.android.clean.architecture.data.datasource.impl.local;

import com.marcohc.android.clean.architecture.data.datasource.inter.MessageDataSource;
import com.marcohc.android.clean.architecture.data.entity.inter.MessageEntity;
import com.marcohc.android.clean.architecture.data.net.RepositoryCallback;

import java.util.HashMap;

public class MessageLocalDataSource implements MessageDataSource {

    @Override
    public void get(Long chatId, Long lastMessageId, String token, RepositoryCallback<Object> callback) {
    }

    @Override
    public void create(MessageEntity message, String token, RepositoryCallback callback) {
    }

    @Override
    public void registerToPushNotifications(HashMap<String, String> map, String token, RepositoryCallback callback) {
    }

    @Override
    public void unregisterFromPushNotifications(HashMap<String, Object> map, String token, RepositoryCallback callback) {
    }

}
