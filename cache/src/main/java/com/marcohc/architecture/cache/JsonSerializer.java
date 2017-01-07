package com.marcohc.architecture.cache;

import android.support.annotation.Nullable;

import com.marcohc.architecture.parser.Parser;
import com.vincentbrison.openlibraries.android.dualcache.CacheSerializer;


/**
 * Json serializer used by {@link com.vincentbrison.openlibraries.android.dualcache.DualCache}.
 *
 * @param <T>
 */
public class JsonSerializer<T> implements CacheSerializer<T> {

    private final Class<T> mClazz;

    public JsonSerializer(Class<T> clazz) {
        this.mClazz = clazz;
    }

    @Nullable
    @Override
    public T fromString(String jsonString) {
        return Parser.parse(jsonString, mClazz);
    }

    @Nullable
    @Override
    public String toString(T object) {
        return Parser.toJsonString(object);
    }
}
