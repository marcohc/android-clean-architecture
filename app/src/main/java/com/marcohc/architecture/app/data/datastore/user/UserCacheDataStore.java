package com.marcohc.architecture.app.data.datastore.user;

import android.support.annotation.NonNull;

import com.marcohc.architecture.app.data.cache.UserCache;
import com.marcohc.architecture.app.domain.entity.UserEntity;

import java.util.List;
import java.util.concurrent.Callable;

import rx.Observable;

/**
 * User disk data store. It uses the android cache to get the data from.
 *
 * @author Marco Hernaiz
 * @since 08/08/16
 */
public class UserCacheDataStore implements UserDiskDataStore {

    @Override
    public Observable<List<UserEntity>> getUsersWithPicture() {
        return Observable.fromCallable(new SaveInCacheCallable(ALL_WITH_PICTURE));
    }

    @Override
    public Observable<List<UserEntity>> getUsersWithoutPicture() {
        return Observable.fromCallable(new SaveInCacheCallable(ALL_WITHOUT_PICTURE));
    }

    @Override
    public boolean isCached(@NonNull String cacheKey) {
        return UserCache.getInstance().isCached(cacheKey);
    }

    @Override
    public void put(@NonNull String cacheKey, @NonNull List<UserEntity> entityList) {
        UserCache.getInstance().put(cacheKey, entityList);
    }

    private static class SaveInCacheCallable implements Callable<List<UserEntity>> {

        private String mKey;

        SaveInCacheCallable(String key) {
            this.mKey = key;
        }

        @Override
        public List<UserEntity> call() throws Exception {
            return UserCache.getInstance().get(mKey);
        }
    }
}
