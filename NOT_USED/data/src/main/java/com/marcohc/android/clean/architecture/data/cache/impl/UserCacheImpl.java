package com.marcohc.android.clean.architecture.data.cache.impl;

import com.marcohc.android.clean.architecture.data.BuildConfig;
import com.marcohc.android.clean.architecture.data.cache.inter.UserCache;
import com.marcohc.android.clean.architecture.domain.entity.inter.UserEntity;
import com.vincentbrison.openlibraries.android.dualcache.lib.DualCache;
import com.vincentbrison.openlibraries.android.dualcache.lib.DualCacheBuilder;

public class UserCacheImpl implements UserCache {

    private static final String CACHE_NAME = BuildConfig.VERSION_NAME;
    private static final int TEST_APP_VERSION = BuildConfig.VERSION_CODE;
    private static final int RAM_MAX_SIZE = 1024 * 1024 * 10;
    private DualCache<UserEntity> cache;

    public UserCacheImpl() {
        cache = new DualCacheBuilder<>(CACHE_NAME, TEST_APP_VERSION, UserEntity.class)
                .useDefaultSerializerInRam(RAM_MAX_SIZE)
                .noDisk();
    }

    @Override
    public UserEntity get(final long userId) {
        return cache.get(String.valueOf(userId));
    }

    @Override
    public void put(UserEntity user) {
        cache.put(String.valueOf(user.getId()), user);
    }

    @Override
    public boolean isCached(final long userId) {
        return cache.get(String.valueOf(userId)) != null;
    }

    @Override
    public boolean isExpired() {
        return false;
    }

    @Override
    public void evictAll() {
        cache.invalidate();
    }
}
