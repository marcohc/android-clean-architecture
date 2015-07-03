package com.marcohc.android.clean.architecture.domain.repository;

import com.marcohc.android.clean.architecture.data.datasource.inter.MessageDataSource;
import com.marcohc.android.clean.architecture.data.datasource.impl.local.MessageLocalDataSource;
import com.marcohc.android.clean.architecture.data.datasource.impl.rest.impl.MessageRemoteDataSource;
import com.marcohc.android.clean.architecture.data.entity.inter.MessageEntity;
import com.marcohc.android.clean.architecture.data.net.RepositoryCallback;
import com.marcohc.android.clean.architecture.data.util.NetworkManager;

import org.json.JSONObject;

import java.util.HashMap;

public class MessageRepository implements Repository {

    // ************************************************************************************************************************************************************************
    // * Attributes
    // ************************************************************************************************************************************************************************

    private static MessageDataSource messageDataSource;
    private static MessageRepository instance;

    // ************************************************************************************************************************************************************************
    // * Initialization methods
    // ************************************************************************************************************************************************************************

    public static MessageRepository getInstance() {
        if (instance == null) {
            instance = new MessageRepository();
            instance.initialize();
        }
        return instance;
    }

    private void initialize() {
        if (NetworkManager.PERSISTENCE_MANAGER.equals(NetworkManager.LOCAL_PERSISTENCE)) {
            messageDataSource = new MessageLocalDataSource();
        } else if (NetworkManager.PERSISTENCE_MANAGER.equals(NetworkManager.REMOTE_PERSISTENCE)) {
            messageDataSource = new MessageRemoteDataSource();
        }
    }

    // ************************************************************************************************************************************************************************
    // * Remote data
    // ************************************************************************************************************************************************************************

    public void get(final Long chatId, final Long lastMessageId, final String token, final RepositoryCallback<Object> callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                messageDataSource.get(chatId, lastMessageId, token, callback);
            }
        }).start();
    }

    public void create(final MessageEntity message, final String token, final RepositoryCallback<JSONObject> callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                messageDataSource.create(message, token, callback);
            }
        }).start();
    }

    public void registerToPushNotifications(final HashMap<String, String> map, final String token, final RepositoryCallback callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                messageDataSource.registerToPushNotifications(map, token, callback);
            }
        }).start();
    }

    public void unregisterFromPushNotifications(final HashMap<String, Object> map, final String token, final RepositoryCallback callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                messageDataSource.unregisterFromPushNotifications(map, token, callback);
            }
        }).start();
    }
}
