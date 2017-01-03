package com.marcohc.architecture.app.data.datastore.user;

import com.marcohc.architecture.app.domain.entity.UserEntity;

import java.util.List;

import rx.Observable;

/**
 * Cloud data store for {@link UserEntity}.
 *
 * @author Marco Hernaiz
 * @since 08/08/16
 */
public interface UserCloudDataStore {

    /**
     * Get all users.
     *
     * @return the observable with users
     */
    Observable<List<UserEntity>> getAll();

}
