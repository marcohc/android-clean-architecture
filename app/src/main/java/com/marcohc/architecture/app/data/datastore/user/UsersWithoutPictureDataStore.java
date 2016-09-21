package com.marcohc.architecture.app.data.datastore.user;

import com.marcohc.architecture.app.data.cache.UserCache;
import com.marcohc.architecture.app.data.service.user.UsersWithoutPictureService;
import com.marcohc.architecture.app.domain.entity.UserEntity;
import com.marcohc.architecture.data.net.ServiceGenerator;

import java.util.List;

import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.functions.Action1;

/**
 * Users without pictures data store.
 * <p>
 * It uses a random user API for it
 *
 * @author Marco Hernaiz
 * @since 08/08/16
 */
public class UsersWithoutPictureDataStore implements UserDataStore {

    private static final String USERS_WITHOUT_PICTURES_URL = "http://jsonplaceholder.typicode.com/";

    @Override
    public Observable<List<UserEntity>> getUsersWithPicture() {
        throw new UnsupportedOperationException("This data source has not users with picture");
    }

    @Override
    public Observable<List<UserEntity>> getUsersWithoutPicture() {

        UsersWithoutPictureService service = ServiceGenerator.getInstance().createService(
                UsersWithoutPictureService.class,
                GsonConverterFactory.create(),
                RxJavaCallAdapterFactory.create(),
                USERS_WITHOUT_PICTURES_URL);

        return service.getAll().doOnNext(new SaveOnCacheAction());
    }

    private static class SaveOnCacheAction implements Action1<List<UserEntity>> {
        @Override
        public void call(List<UserEntity> userEntityList) {
            UserCache.getInstance().put(UserCache.ALL_WITHOUT_PICTURE, userEntityList);
        }
    }
}
