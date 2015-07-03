package com.marcohc.android.clean.architecture.domain.repository;

import com.marcohc.android.clean.architecture.common.net.RepositoryCallback;

import org.json.JSONObject;

import java.util.HashMap;

public class MessageRepository {

    // ************************************************************************************************************************************************************************
    // * Attributes
    // ************************************************************************************************************************************************************************

    private static MessageDal messageDal;
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
            throw new ExceptionInInitializerError("Local persistence it's not developed yet!");
        } else if (NetworkManager.PERSISTENCE_MANAGER.equals(NetworkManager.REMOTE_PERSISTENCE)) {
            messageDal = new MessageDalImpl();
        }
    }

    // ************************************************************************************************************************************************************************
    // * Remote data
    // ************************************************************************************************************************************************************************

    public void get(final Long chatId, final Long lastMessageId, final String token, final RepositoryCallback<Object> callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                messageDal.get(chatId, lastMessageId, token, callback);
            }
        }).start();
    }

    public void create(final MessageEntity message, final String token, final RepositoryCallback<JSONObject> callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                messageDal.create(message, token, callback);
            }
        }).start();
    }

    public void registerToPushNotifications(final HashMap<String, String> map, final String token, final RepositoryCallback callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                messageDal.registerToPushNotifications(map, token, callback);
            }
        }).start();
    }

    public void unregisterFromPushNotifications(final HashMap<String, Object> map, final String token, final RepositoryCallback callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                messageDal.unregisterFromPushNotifications(map, token, callback);
            }
        }).start();
    }
}
