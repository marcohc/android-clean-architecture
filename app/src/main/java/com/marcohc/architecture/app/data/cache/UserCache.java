package com.marcohc.architecture.app.data.cache;

import com.marcohc.architecture.app.BuildConfig;
import com.marcohc.architecture.app.domain.entity.UserEntity;
import com.marcohc.architecture.app.internal.di.ApplicationInjector;
import com.marcohc.architecture.common.helper.GenericCollection;
import com.marcohc.architecture.common.helper.ParserHelper;
import com.marcohc.architecture.common.helper.StringHelper;
import com.marcohc.architecture.data.cache.DataCache;
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

    private static UserCache sInstance;
    private DualCache<String> mCache;
    private static final long CACHE_EXPIRATION_TIME = 3600 * 1000;
    private static final String CACHE_NAME = UserCache.class.getSimpleName() + BuildConfig.VERSION_NAME;
    private static final int TEST_APP_VERSION = BuildConfig.VERSION_CODE;
    private static final int RAM_MAX_SIZE = 1024 * 1024 * 512;

    private UserCache() {
        ApplicationInjector.getApplicationComponent().inject(this);
        CacheSerializer<String> jsonSerializer = new JsonSerializer<>(String.class);
        mCache = new Builder<>(CACHE_NAME, TEST_APP_VERSION, String.class)
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
    public boolean isCached(String key) {

        if (StringHelper.isBlank(key)) {
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

        if (StringHelper.isBlank(key)) {
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
    @SuppressWarnings("unchecked")
    public List<UserEntity> get(String key) {
        String jsonStored = mCache.get(key);
        return ParserHelper.getInstance().parseCollection(jsonStored, new GenericCollection<>(List.class, UserEntity.class));
    }

    @Override
    public void put(String key, List<UserEntity> item) {

        if (StringHelper.isBlank(key)) {
            Timber.e("put: key is null!");
            return;
        }

        mCache.put(key, ParserHelper.getInstance().toJsonString(item));
        mCache.put(getTimeToken(key), String.valueOf(System.currentTimeMillis()));
    }

    @Override
    public void remove(String key) {

        if (StringHelper.isBlank(key)) {
            Timber.e("remove: key is null!");
            return;
        }

        mCache.delete(key);
        mCache.delete(getTimeToken(key));
    }

    @Override
    public void clear() {
        mCache.invalidate();
    }

    private String getTimeToken(String key) {
        return key + "_timestamp";
    }
}
