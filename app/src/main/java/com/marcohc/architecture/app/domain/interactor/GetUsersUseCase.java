package com.marcohc.architecture.app.domain.interactor;

import android.support.annotation.NonNull;

import com.marcohc.architecture.app.data.repository.user.UserRepository;
import com.marcohc.architecture.app.data.repository.user.UserRepositoryImpl;
import com.marcohc.architecture.app.domain.entity.UserEntity;
import com.marcohc.architecture.app.domain.mapper.UserMapper;
import com.marcohc.architecture.app.domain.model.UserModel;
import com.marcohc.architecture.rx.domain.interactor.BaseRxUseCase;

import java.util.List;

import rx.Observable;
import rx.functions.Func1;

/**
 * Get a list of users.
 *
 * @author Marco Hernaiz
 * @since 08/08/16
 */
public class GetUsersUseCase extends BaseRxUseCase<List<UserModel>> {

    private final boolean mWithPicture;
    private final boolean mUseCache;
    private final UserRepository mUserRepository;

    public GetUsersUseCase(boolean withPicture, boolean useCache) {
        mWithPicture = withPicture;
        mUseCache = useCache;
        mUserRepository = new UserRepositoryImpl();
    }

    @NonNull
    @Override
    protected Observable<List<UserModel>> getObservable() {
        if (mWithPicture) {
            return mUserRepository
                    .getAllWithPicture(mUseCache)
                    .map(new Func1<List<UserEntity>, List<UserModel>>() {
                        @Override
                        public List<UserModel> call(List<UserEntity> userEntities) {
                            return UserMapper.getInstance().transformEntityList(userEntities);
                        }
                    });
        } else {
            return mUserRepository
                    .getAllWithoutPicture(mUseCache)
                    .map(new Func1<List<UserEntity>, List<UserModel>>() {
                        @Override
                        public List<UserModel> call(List<UserEntity> userEntities) {
                            return UserMapper.getInstance().transformEntityList(userEntities);
                        }
                    });
        }
    }

}
