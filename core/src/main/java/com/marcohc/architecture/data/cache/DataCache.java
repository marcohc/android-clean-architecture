package com.marcohc.architecture.data.cache;

import android.support.annotation.Nullable;

/**
 * An interface representing a Cache.
 *
 * @param <E> the type of object you want to cache
 * @author Marco Hernaiz
 * @since 09/08/16
 */
public interface DataCache<E> {

    /**
     * Check if the object which belongs to this key exists.
     *
     * @param key the object key
     * @return true if is cached else if not
     */
    boolean isCached(@Nullable String key);

    /**
     * Check if the object which belongs to this key exists and is valid (not expired).
     *
     * @param key the object key
     * @return true if is valid else if not
     */
    boolean isValid(@Nullable String key);

    /**
     * Return the cached object which belongs to this key or null otherwise.
     *
     * @param key the object key
     * @return the object store or null
     */
    @Nullable
    E get(@Nullable String key);

    /**
     * Put the pair key and item inside of the cache.
     *
     * @param key  the object key
     * @param item the object you want to store
     */
    void put(@Nullable String key, @Nullable E item);

    /**
     * Remove the specific object which belongs to this key if exists.
     *
     * @param key the object key
     */
    void remove(@Nullable String key);

    /**
     * Clear the entire cache.
     */
    void clear();
}
