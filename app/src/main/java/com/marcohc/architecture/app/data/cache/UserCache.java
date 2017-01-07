package com.marcohc.architecture.app.data.cache;

import com.marcohc.architecture.app.BuildConfig;
import com.marcohc.architecture.app.domain.entity.UserEntity;
import com.marcohc.architecture.app.internal.di.ApplicationInjector;
import com.marcohc.architecture.cache.DataCache;
import com.marcohc.architecture.cache.JsonSerializer;
import com.marcohc.architecture.common.util.helper.StringUtils;
import com.marcohc.architecture.parser.GenericCollection;
import com.marcohc.architecture.parser.Parser;
import com.vincentbrison.openlibraries.android.dualcache.Builder;
import com.vincentbrison.openlibraries.android.dualcache.CacheSerializer;
import com.vincentbrison.openlibraries.android.dualcache.DualCache;

import java.util.List;

import timber.log.Timber;

/**
 * User mCache which keeps the response from data sources in the android mCache using {@link DualCache}.
 *
 * @author Marco Hernaiz
 * @since 08/08/16
 */
public final class UserCache implements DataCache<List<UserEntity>> {

    private static final long CACHE_EXPIRATION_TIME = 3600 * 1000;
    private static final String CACHE_NAME = UserCache.class.getSimpleName() + BuildConfig.VERSION_NAME;
    private static final int TEST_APP_VERSION = BuildConfig.VERSION_CODE;
    private static final int RAM_MAX_SIZE = 1024 * 1024 * 512;
    private static UserCache sInstance;
    private final DualCache<String> mCache;

    private UserCache() {
        ApplicationInjector.getApplicationComponent().inject(this);
        CacheSerializer<String> jsonSerializer = new JsonSerializer<>(String.class);
        mCache = new Builder<String>(CACHE_NAME, TEST_APP_VERSION)
                .useSerializerInRam(RAM_MAX_SIZE, jsonSerializer)
                .useSerializerInDisk(RAM_MAX_SIZE, true, jsonSerializer, ApplicationInjector.getApplicationComponent().provideContext())
                .build();
    }

    /**
     * Get singleton instance.
     *
     * @return UserCache
     */
    public synchronized static UserCache getInstance() {
        if (sInstance == null) {
            sInstance = new UserCache();
        }
        return sInstance;
    }

    @Override
    public void clear() {
        mCache.invalidate();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<UserEntity> get(String key) {
        String jsonStored = mCache.get(key);
        return Parser.parseCollection(jsonStored, new GenericCollection<>(List.class, UserEntity.class));
    }

    @Override
    public boolean isCached(String key) {

        if (StringUtils.isBlank(key)) {
            Timber.e("isCached: key is null!");
            return false;
        }

        if (mCache.get(key) != null) {
            Timber.v("isCached: true");
            return true;
        } else {
            Timber.v("isCached: false");
            return false;
        }
    }

    @Override
    public boolean isValid(String key) {

        if (StringUtils.isBlank(key)) {
            Timber.e("isValid: key is null!");
            return false;
        }

        String jsonStored = mCache.get(key);
        Long timeStored = null;
        try {
            timeStored = Long.valueOf(mCache.get(getTimeToken(key)));
        } catch (NumberFormatException e) {
            Timber.e("isValid: timeStored is not a Long!");
        }

        boolean isValid = false;
        if (jsonStored != null && timeStored != null) {
            Timber.v("isValid: is stored");

            if (((System.currentTimeMillis() - timeStored) < CACHE_EXPIRATION_TIME)) {
                Timber.v("isValid: true");
                isValid = true;
            } else {
                Timber.v("isValid: is expired");
            }
        } else {
            Timber.v("isValid: false");
        }

        Timber.v("isValid: %s", isValid);
        return isValid;
    }

    @Override
    public void put(String key, List<UserEntity> item) {

        if (StringUtils.isBlank(key)) {
            Timber.e("put: key is null!");
            return;
        }

        mCache.put(key, Parser.toJsonString(item));
        mCache.put(getTimeToken(key), String.valueOf(System.currentTimeMillis()));
    }

    @Override
    public void remove(String key) {

        if (StringUtils.isBlank(key)) {
            Timber.e("remove: key is null!");
            return;
        }

        mCache.delete(key);
        mCache.delete(getTimeToken(key));
    }

    private String getTimeToken(String key) {
        return key + "_timestamp";
    }
}
