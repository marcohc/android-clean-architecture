package com.marcohc.architecture.app.domain.interactor;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.marcohc.architecture.app.data.repository.user.UserRepository;
import com.marcohc.architecture.app.data.repository.user.UserRepositoryImpl;
import com.marcohc.architecture.app.domain.entity.UserEntity;
import com.marcohc.architecture.app.domain.mapper.UserMapper;
import com.marcohc.architecture.app.domain.model.UserModel;
import com.marcohc.architecture.domain.exception.DomainException;
import com.marcohc.architecture.rx.domain.interactor.BaseRxUseCase;

import java.util.List;

import rx.Observable;

/**
 * Get a list of users.
 *
 * @author Marco Hernaiz
 * @since 08/08/16
 */
public class GetUsersUseCase extends BaseRxUseCase<List<UserModel>, List<UserEntity>> {

    // ************************************************************************************************************************************************************************
    // * Attributes
    // ************************************************************************************************************************************************************************

    private final boolean mWithPicture;
    private final boolean mUseCache;
    private final UserRepository mUserRepository;

    // ************************************************************************************************************************************************************************
    // * Constructors
    // ************************************************************************************************************************************************************************

    public GetUsersUseCase(boolean withPicture, boolean useCache) {
        mWithPicture = withPicture;
        mUseCache = useCache;
        mUserRepository = new UserRepositoryImpl();
    }

    // ************************************************************************************************************************************************************************
    // * Use case execution
    // ************************************************************************************************************************************************************************

    @NonNull
    @Override
    protected Observable<List<UserEntity>> getObservable() {
        return mUserRepository.getAll(mWithPicture, mUseCache);
    }

    @Nullable
    @Override
    protected List<UserModel> transformData(@NonNull List<UserEntity> dataResponse) {
        return UserMapper.getInstance().transformEntityList(dataResponse);
    }

    @Override
    protected void doBusinessLogic(@Nullable List<UserModel> domainResponse) {
        // Do business logic here, in this case, nothing
    }

    // ************************************************************************************************************************************************************************
    // * Error handling methods
    // ************************************************************************************************************************************************************************

    @Override
    protected void onDataException(@NonNull Throwable throwable) throws DomainException {
        // Catch and throw you exceptions here
    }

}
