package com.marcohc.android.clean.architecture.data.cache.impl;

import com.marcohc.android.clean.architecture.common.util.Constants;
import com.marcohc.android.clean.architecture.data.cache.inter.Cache;
import com.marcohc.android.clean.architecture.domain.model.UserModel;
import com.marcohc.android.clean.architecture.presentation.util.PreferencesConstants;
import com.marcohc.android.clean.architecture.sample.BuildConfig;
import com.marcohc.helperoid.ParserHelper;
import com.marcohc.helperoid.PreferencesHelper;
import com.vincentbrison.openlibraries.android.dualcache.lib.DualCache;
import com.vincentbrison.openlibraries.android.dualcache.lib.DualCacheBuilder;

import timber.log.Timber;

public class UserCache implements Cache<UserModel> {

    private static final String CACHE_NAME = UserCache.class.getSimpleName() + BuildConfig.VERSION_NAME;
    private static final int TEST_APP_VERSION = BuildConfig.VERSION_CODE;
    private static final int RAM_MAX_SIZE = 1024 * 1024 * 10;
    private static DualCache<UserModel> cache;
    private static UserCache instance;

    public static UserCache getInstance() {
        if (instance == null) {
            instance = new UserCache();
            cache = new DualCacheBuilder<>(CACHE_NAME, TEST_APP_VERSION, UserModel.class)
                    .noRam()
                    .useDefaultSerializerInDisk(RAM_MAX_SIZE, true);
//                    .useDefaultSerializerInRam(RAM_MAX_SIZE)
//                    .noDisk();
        }
        return instance;
    }

    public UserModel getCurrentUser() {
        String userKey = PreferencesHelper.getString(PreferencesConstants.USER_KEY, null);
        if (userKey == null) {
            return null;
        } else {
            try {
                UserModel user = get(userKey);
                // Load the user for the first time from the shared preferences
                if (user == null) {
                    user = ParserHelper.parse(PreferencesHelper.getString(PreferencesConstants.USER, ""), UserModel.class);
                    if (user != null) {
                        putCurrentUser(userKey, user);
                    }
                }
                return user;
            } catch (Exception e) {
                Timber.e(Constants.LOG_TAG, String.format("User couldn't recover from preferences: %s", e.getMessage()));
                return null;
            }
        }
    }

    public void putCurrentUser(String key, UserModel user) {
        PreferencesHelper.putString(PreferencesConstants.USER_KEY, key);
        PreferencesHelper.putString(PreferencesConstants.USER, user.toJsonString());
        put(key, user);
    }

    public void removeCurrentUser() {
        PreferencesHelper.remove(PreferencesConstants.USER_KEY);
        PreferencesHelper.remove(PreferencesConstants.USER);
        invalidate();
    }

    @Override
    public UserModel get(String key) {
        return cache.get(CacheUtil.getKey(key));
    }

    @Override
    public void put(UserModel item) {
        put(item.getKey(), item);
    }

    @Override
    public void put(String key, UserModel item) {
        cache.put(CacheUtil.getKey(key), item);
    }

    @Override
    public boolean isCached(final String key) {
        return cache.get(CacheUtil.getKey(key)) != null;
    }

    @Override
    public void invalidate() {
        cache.invalidate();
    }
}
