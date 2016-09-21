package com.marcohc.architecture.app.data.repository.user;

import com.marcohc.architecture.app.data.factory.UserDataStoreFactory;
import com.marcohc.architecture.app.domain.entity.UserEntity;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

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

    // ************************************************************************************************************************************************************************
    // * Constructors
    // ************************************************************************************************************************************************************************

    @Inject
    public UserRepositoryImpl() {
    }

    // ************************************************************************************************************************************************************************
    // * Event handler methods
    // ************************************************************************************************************************************************************************

    /**
     * The repository is discriminating the data source based on the withPicture.
     * But it doesn't know from where the data is coming (Cloud or Disk in this case)
     *
     * @param withPicture users with picture
     * @param useCache    use of the cache
     * @return Observable
     */
    @Override
    public Observable<List<UserEntity>> getAll(boolean withPicture, boolean useCache) {
        if (withPicture) {
            return UserDataStoreFactory.getInstance().getUsersWithPictureDataStore(useCache).getUsersWithPicture();
        } else {
            return UserDataStoreFactory.getInstance().getUsersWithoutPictureDataStore(useCache).getUsersWithoutPicture();
        }
    }

}
