package com.marcohc.android.clean.architecture.data.cache.inter;



/**
 * An interface representing a user Cache.
 */
public interface Cache<E> {

    /**
     * Gets an {@link UserEntity}.
     */
    E get(String key);

    /**
     * Puts and element into the cache.
     *
     * @param item Element to insert in the cache.
     */
    void put(E item);

    void put(String key, E item);

    /**
     * Checks if an element (item) exists in the cache.
     *
     * @return true if the element is cached, otherwise false.
     */
    boolean isCached(final String key);

    /**
     * Evict all elements of the cache.
     */
    void invalidate();
}
