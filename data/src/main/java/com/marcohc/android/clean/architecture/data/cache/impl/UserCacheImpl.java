package com.marcohc.android.clean.architecture.data.cache.impl;

import com.marcohc.android.clean.architecture.data.cache.inter.UserCache;
import com.marcohc.android.clean.architecture.data.util.PreferencesManager;
import com.marcohc.android.clean.architecture.domain.model.UserModel;
import com.marcohc.helperoid.ParserHelper;

public class UserCacheImpl implements UserCache {

    @Override
    public UserModel get() {
        return ParserHelper.parseJson(PreferencesManager.getUser(), UserModel.class);
    }

    @Override
    public void put(UserModel user) {
        PreferencesManager.saveUser(ParserHelper.toJsonString(user));
    }

    @Override
    public void remove() {
        PreferencesManager.removeUser();
        evictAll();
    }

    @Override
    public boolean isCached() {
        return false;
    }

    @Override
    public boolean isExpired() {
        return false;
    }

    @Override
    public void evictAll() {

    }
}
