package com.xs2mobile.buurapp.presentation.util;

import android.content.Context;

import com.marcohc.helperoid.ParserHelper;
import com.marcohc.helperoid.PreferencesHelper;
import com.xs2mobile.buurapp.domain.repository.UserRepository;
import com.xs2mobile.buurapp.presentation.notification.NotificationManager;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class PreferencesManager {

    // ************************************************************************************************************************************************************************
    // * Constants
    // ************************************************************************************************************************************************************************

    public static final String SHARED_PREFERENCES_NAME = "com.xs2mobile.buurapp_preferences";
    private static final String USERS_LIST = "users_list";
    private static final String CHATS_LIST = "chats_list";
    private static final String CURRENT_USER = "current_user";
    private static final String CHAT_COUNT_MAP = "chat_count_map";
    private static final String REGISTRATION_ID = "registration_id";

    // ************************************************************************************************************************************************************************
    // * Initialization methods
    // ************************************************************************************************************************************************************************

    public static void initialize(Context context) {
        PreferencesHelper.initialize(context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE));
    }

    // ************************************************************************************************************************************************************************
    // * User methods
    // ************************************************************************************************************************************************************************

    public static void saveUser(String userJson) {
        PreferencesHelper.putString(CURRENT_USER, userJson);
    }

    public static String getUser() {
        return PreferencesHelper.getString(CURRENT_USER, "");
    }

    public static void saveUsersList(String userListJson) {
        PreferencesHelper.putString(USERS_LIST, userListJson);
    }

    public static String getUsersList() {
        return PreferencesHelper.getString(USERS_LIST, "");
    }

    public static void removeUserData() {
        NotificationManager.getInstance().unregisterInBackground(UserRepository.getInstance().getCurrentUser().getToken());
        PreferencesManager.removeRegistrationId();
        removeUser();
    }

    public static void removeUser() {
        PreferencesHelper.remove(CURRENT_USER);
    }

    public static void removeRegistrationId() {
        PreferencesHelper.remove(REGISTRATION_ID);
    }

    public static void saveRegistrationId(String registrationId) {
        PreferencesHelper.putString(REGISTRATION_ID, registrationId);
    }

    public static String getRegistrationId() {
        return PreferencesHelper.getString(REGISTRATION_ID, "");
    }

    // ************************************************************************************************************************************************************************
    // * Chat methods
    // ************************************************************************************************************************************************************************

    public static void saveChatsList(String list) {
        PreferencesHelper.putString(CHATS_LIST, list);
    }

    public static String getChatsList() {
        return PreferencesHelper.getString(CHATS_LIST, "");
    }

    public static void increaseChatCount(Long chatId) {
        Map<String, Integer> map = getChatCountMap();
        int chatCount = map.containsKey(String.valueOf(chatId)) ? map.get(String.valueOf(chatId)) : 0;
        map.put(String.valueOf(chatId), chatCount + 1);
        PreferencesHelper.putString(CHAT_COUNT_MAP, ParserHelper.toJsonString(map));
    }

    public static void resetChatCount(Long chatId) {
        Map<String, Integer> map = getChatCountMap();
        map.put(String.valueOf(chatId), 0);
        PreferencesHelper.putString(CHAT_COUNT_MAP, ParserHelper.toJsonString(map));
    }

    public static int getChatCount(Long chatId) {
        Map<String, Integer> map = getChatCountMap();
        return map.containsKey(String.valueOf(chatId)) ? map.get(String.valueOf(chatId)) : 0;
    }

    private static Map<String, Integer> getChatCountMap() {
        String chatCountMapString = PreferencesHelper.getString(CHAT_COUNT_MAP, "");
        return StringUtils.isBlank(chatCountMapString) ? new HashMap() : ParserHelper.parseJson(chatCountMapString, HashMap.class);
    }

    // ************************************************************************************************************************************************************************
    // * UI management methods
    // ************************************************************************************************************************************************************************

}
