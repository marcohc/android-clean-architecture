package com.marcohc.architecture.app.domain.util;

import com.marcohc.architecture.app.domain.model.UserModel;
import com.marcohc.architecture.app.presentation.util.PreferencesConstants;
import com.marcohc.architecture.common.helper.ParserHelper;
import com.marcohc.architecture.common.helper.PreferencesHelper;
import com.marcohc.architecture.common.helper.StringHelper;

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
            return ParserHelper.getInstance().parse(PreferencesHelper.getInstance().getString(PreferencesConstants.USER, null), UserModel.class);
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

        PreferencesHelper.getInstance().putString(PreferencesConstants.USER, user.toJsonString());
    }

    public void logOut() {
        PreferencesHelper.getInstance().remove(PreferencesConstants.USER);
    }
}