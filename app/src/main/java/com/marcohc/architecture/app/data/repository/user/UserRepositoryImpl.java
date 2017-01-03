package com.marcohc.architecture.app.data.repository.user;

import com.marcohc.architecture.app.data.datastore.user.UserCloudDataStore;
import com.marcohc.architecture.app.data.datastore.user.UserDiskDataStore;
import com.marcohc.architecture.app.data.factory.UserDataStoreFactory;
import com.marcohc.architecture.app.domain.entity.UserEntity;

import java.util.List;

import javax.inject.Singleton;

import rx.Observable;
import rx.functions.Action1;

import static com.marcohc.architecture.app.data.datastore.user.UserDiskDataStore.ALL_WITHOUT_PICTURE;
import static com.marcohc.architecture.app.data.datastore.user.UserDiskDataStore.ALL_WITH_PICTURE;

/**
 * A repository class must be created for each model in the application.
 * <p>
 * It provides data retrieved from its factory
 *
 * @author Marco Hernaiz
 * @since 08/08/16
 */
@Singleton
public class UserRepositoryImpl implements UserRepository {

    private final UserDiskDataStore diskDataStore;
    private final UserCloudDataStore userWithPicturesCloudDataStore;
    private final UserCloudDataStore userWithoutPicturesCloudDataStore;

    public UserRepositoryImpl() {
        diskDataStore = UserDataStoreFactory.createUserDiskDataStore();
        userWithPicturesCloudDataStore = UserDataStoreFactory.createUserWithPicturesCloudDataStore();
        userWithoutPicturesCloudDataStore = UserDataStoreFactory.createUserWithoutPicturesCloudDataStore();
    }

    @Override
    public Observable<List<UserEntity>> getAllWithPicture(boolean useCache) {
        final String cacheKey = ALL_WITH_PICTURE;
        if (useCache && diskDataStore.isCached(cacheKey)) {
            return diskDataStore.getUsersWithPicture();
        } else {
            return userWithPicturesCloudDataStore
                    .getAll()
                    .doOnNext(new Action1<List<UserEntity>>() {
                        @Override
                        public void call(List<UserEntity> entityList) {
                            diskDataStore.put(cacheKey, entityList);
                        }
                    });
        }
    }

    @Override
    public Observable<List<UserEntity>> getAllWithoutPicture(boolean useCache) {
        final String cacheKey = ALL_WITHOUT_PICTURE;
        if (useCache && diskDataStore.isCached(cacheKey)) {
            return diskDataStore.getUsersWithoutPicture();
        } else {
            return userWithoutPicturesCloudDataStore
                    .getAll()
                    .doOnNext(new Action1<List<UserEntity>>() {
                        @Override
                        public void call(List<UserEntity> entityList) {
                            diskDataStore.put(cacheKey, entityList);
                        }
                    });
        }
    }

}
