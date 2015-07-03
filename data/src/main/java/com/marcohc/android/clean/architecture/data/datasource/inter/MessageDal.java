package com.marcohc.android.clean.architecture.data.datasource.inter;

import com.marcohc.android.clean.architecture.common.net.RepositoryCallback;
import com.marcohc.android.clean.architecture.data.entity.inter.MessageEntity;

import java.util.HashMap;

public interface MessageDal {

    void get(Long chatId, Long lastMessageId, String token, RepositoryCallback<Object> callback);

    void create(MessageEntity message, String token, RepositoryCallback callback);

    void registerToPushNotifications(HashMap<String, String> map, String token, RepositoryCallback callback);

    void unregisterFromPushNotifications(HashMap<String, Object> map, String token, RepositoryCallback callback);
}
