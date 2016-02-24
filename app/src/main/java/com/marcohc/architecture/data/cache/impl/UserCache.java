package com.marcohc.architecture.data.cache.impl;

import com.marcohc.architecture.data.cache.inter.Cache;
import com.marcohc.architecture.domain.model.UserModel;
import com.marcohc.architecture.sample.BuildConfig;
import com.vincentbrison.openlibraries.android.dualcache.lib.DualCache;
import com.vincentbrison.openlibraries.android.dualcache.lib.DualCacheBuilder;

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
