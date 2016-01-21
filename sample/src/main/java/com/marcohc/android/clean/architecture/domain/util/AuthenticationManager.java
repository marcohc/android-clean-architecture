package com.marcohc.android.clean.architecture.domain.util;

import com.marcohc.android.clean.architecture.domain.model.UserModel;
import com.marcohc.android.clean.architecture.presentation.util.PreferencesConstants;
import com.marcohc.helperoid.ParserHelper;
import com.marcohc.helperoid.PreferencesHelper;
import com.marcohc.helperoid.StringHelper;

import timber.log.Timber;

// TODO: Use library to encrypt data
public class AuthenticationManager {

    private static AuthenticationManager instance;

    public static AuthenticationManager getInstance() {
        if (instance == null) {
            instance = new AuthenticationManager();
        }
        return instance;
    }

    public Boolean isLoggedIn() {
        return getCurrentUser() != null;
    }

    public UserModel getCurrentUser() {
        try {
            return ParserHelper.parse(PreferencesHelper.getString(PreferencesConstants.USER, ""), UserModel.class);
        } catch (Exception e) {
            Timber.e("User couldn't be recovered from preferences: %s", e.getMessage());
            return null;
        }
    }

    public void logIn(String key, UserModel user) {

        if (StringHelper.isEmpty(key)) {
            Timber.e("key is empty!");
            return;
        }

        if (user == null) {
            Timber.e("user is null!");
            return;
        }

        PreferencesHelper.putString(PreferencesConstants.USER, user.toJsonString());
    }

    public void logOut() {
        PreferencesHelper.remove(PreferencesConstants.USER);
    }
}
