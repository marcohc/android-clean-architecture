package com.marcohc.architecture.app.data.datastore.user;

import com.marcohc.architecture.app.data.cache.UserCache;
import com.marcohc.architecture.app.data.service.user.UsersWithPictureService;
import com.marcohc.architecture.app.domain.entity.UserEntity;
import com.marcohc.architecture.app.domain.entity.UsersWithPictureEntity;
import com.marcohc.architecture.app.domain.mapper.UserMapper;
import com.marcohc.architecture.rx.data.net.ServiceGenerator;

import java.util.List;

import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Users with pictures data store.
 * <p>
 * It uses a random user API for it
 *
 * @author Marco Hernaiz
 * @since 08/08/16
 */
public class UsersWithPictureDataStore implements UserDataStore {

    private static final String USERS_WITH_PICTURES_URL = "http://api.randomuser.me/";

    @Override
    public Observable<List<UserEntity>> getUsersWithPicture() {

        UsersWithPictureService service = ServiceGenerator.getInstance().createService(
                UsersWithPictureService.class,
                GsonConverterFactory.create(),
                RxJavaCallAdapterFactory.create(),
                USERS_WITH_PICTURES_URL);

        return service.getAll().map(new ParseResponseFunction()).doOnNext(new SaveOnCacheAction());
    }

    @Override
    public Observable<List<UserEntity>> getUsersWithoutPicture() {
        throw new UnsupportedOperationException("This data source has not users without picture");
    }

    private static class ParseResponseFunction implements Func1<UsersWithPictureEntity, List<UserEntity>> {
        @Override
        public List<UserEntity> call(UsersWithPictureEntity usersWithPictureEntity) {
            return UserMapper.getInstance().parseResponse(usersWithPictureEntity);
        }
    }

    private static class SaveOnCacheAction implements Action1<List<UserEntity>> {
        @Override
        public void call(List<UserEntity> userEntityList) {
            UserCache.getInstance().put(UserCache.ALL_WITH_PICTURE, userEntityList);
        }
    }

}
