package com.marcohc.architecture.app.data.repository.user;

import com.marcohc.architecture.app.domain.entity.UserEntity;

import java.util.List;

import rx.Observable;

/**
 * User repository.
 *
 * @author Marco Hernaiz
 * @since 08/08/16
 */
public interface UserRepository {

    /**
     * Get all users based on parameters.
     *
     * @param withPicture get users with picture
     * @param useCache    use the cache
     * @return the Observable with a list of users
     */
    Observable<List<UserEntity>> getAll(boolean withPicture, boolean useCache);

}
