package com.marcohc.architecture.app.data.datastore.user;

import com.marcohc.architecture.app.domain.entity.UserEntity;

import java.util.List;

import rx.Observable;

/**
 * Specific methods for this model go here.
 *
 * @author Marco Hernaiz
 * @since 08/08/16
 */
public interface UserDataStore {

    /**
     * Get users with picture.
     *
     * @return the observable with users
     */
    Observable<List<UserEntity>> getUsersWithPicture();

    /**
     * Get users without pictures.
     *
     * @return the observable with users
     */
    Observable<List<UserEntity>> getUsersWithoutPicture();
}
