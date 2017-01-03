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
     * Get all users with picture based on parameters.
     *
     * @param useCache use the cache
     * @return the Observable with a list of users
     */
    Observable<List<UserEntity>> getAllWithPicture(boolean useCache);

    /**
     * Get all users without picture based on parameters.
     *
     * @param useCache use the cache
     * @return the Observable with a list of users
     */
    Observable<List<UserEntity>> getAllWithoutPicture(boolean useCache);

}
