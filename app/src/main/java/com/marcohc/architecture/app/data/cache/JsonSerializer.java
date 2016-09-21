package com.marcohc.architecture.app.data.cache;

import com.marcohc.architecture.common.helper.ParserHelper;
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

    @Override
    public T fromString(String jsonString) {
        return ParserHelper.getInstance().parse(jsonString, mClazz);
    }

    @Override
    public String toString(T object) {
        return ParserHelper.getInstance().toJsonString(object);
    }
}
