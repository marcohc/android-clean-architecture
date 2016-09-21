package com.marcohc.architecture.app.data.datastore.user;

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
public class UserDiskDataStore implements UserDataStore {

    @Override
    public Observable<List<UserEntity>> getUsersWithPicture() {
        return Observable.fromCallable(new SaveInCacheCallable(UserCache.ALL_WITH_PICTURE));
    }

    @Override
    public Observable<List<UserEntity>> getUsersWithoutPicture() {
        return Observable.fromCallable(new SaveInCacheCallable(UserCache.ALL_WITHOUT_PICTURE));
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
