package com.marcohc.architecture.data.cache;

/**
 * An interface representing a Cache.
 */
public interface DataCache<E> {

    E get(String key);

    void put(String key, E item);

    boolean isCached(String key);

    boolean isValid(String key);

    void invalidate();
}
