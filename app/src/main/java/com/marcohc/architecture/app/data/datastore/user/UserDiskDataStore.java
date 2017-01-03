package com.marcohc.architecture.app.data.datastore.user;

import com.marcohc.architecture.app.domain.entity.UserEntity;

import java.util.List;

import rx.Observable;

/**
 * Disk data store for {@link UserEntity}.
 *
 * @author Marco Hernaiz
 * @since 08/08/16
 */
public interface UserDiskDataStore {

    String ALL_WITH_PICTURE = "all_with_picture";
    String ALL_WITHOUT_PICTURE = "all_without_picture";

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

    /**
     * Checks if the value is stored.
     *
     * @param cacheKey the key for the cache
     * @return true if is store, false otherwise
     */
    boolean isCached(String cacheKey);

    /**
     * Puts the list in the cache with the specific cache key.
     *
     * @param cacheKey the key for the cache
     * @param entityList the list of entities to persist
     */
    void put(String cacheKey, List<UserEntity> entityList);
}
