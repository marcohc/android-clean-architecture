package com.marcohc.architecture.data.cache;

/**
 * An interface representing a Cache.
 */
public interface DataCache<E> {

    boolean isCached(String key);

    boolean isValid(String key);

    E get(String key);

    void put(String key, E item);

    void remove(String key);

    void clear();
}
