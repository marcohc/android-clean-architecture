package com.marcohc.android.clean.architecture.data.datasource.inter;


import com.marcohc.android.clean.architecture.domain.entity.MessageEntity;

import java.util.HashMap;

public interface MessageDataSource {

    void get(Long chatId, Long lastMessageId, String token);

    void create(MessageEntity message, String token);

    void registerToPushNotifications(HashMap<String, String> map, String token);

    void unregisterFromPushNotifications(HashMap<String, Object> map, String token);
}
