package com.marcohc.android.clean.architecture.data.cache.inter;

import com.marcohc.android.clean.architecture.domain.entity.UserEntity;

/**
 * An interface representing a user Cache.
 */
public interface UserCache {

    /**
     * Gets an {@link UserEntity}.
     */
    UserEntity get(long userId);

    /**
     * Puts and element into the cache.
     *
     * @param user Element to insert in the cache.
     */
    void put(UserEntity user);

    /**
     * Checks if an element (User) exists in the cache.
     *
     * @return true if the element is cached, otherwise false.
     */
    boolean isCached(final long userId);

    /**
     * Checks if the cache is expired.
     *
     * @return true, the cache is expired, otherwise false.
     */
    boolean isExpired();

    /**
     * Evict all elements of the cache.
     */
    void evictAll();
}
