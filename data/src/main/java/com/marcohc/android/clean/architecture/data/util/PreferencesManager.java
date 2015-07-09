package com.marcohc.android.clean.architecture.data.util;

import android.content.Context;

import com.marcohc.helperoid.PreferencesHelper;

public class PreferencesManager {

    // ************************************************************************************************************************************************************************
    // * Constants
    // ************************************************************************************************************************************************************************

    public static final String SHARED_PREFERENCES_NAME = "com.marcohc.android.clean.architecture_preferences";
    private static final String CURRENT_USER = "current_user";
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

    public static void removeUser() {
        PreferencesHelper.remove(CURRENT_USER);
    }

    public static void removeRegistrationId() {
        PreferencesHelper.remove(REGISTRATION_ID);
    }

}
