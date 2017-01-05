package com.marcohc.architecture.parser;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class GenericMap<Map, Key, Value> implements ParameterizedType {

    private final Class<Map> mapClass;
    private final Class<Key> keyClass;
    private final Class<Value> valueClass;

    public GenericMap(Class<Map> mapClass, Class<Key> keyClass, Class<Value> valueClass) {
        this.mapClass = mapClass;
        this.keyClass = keyClass;
        this.valueClass = valueClass;
    }

    public Type[] getActualTypeArguments() {
        return new Type[]{keyClass, valueClass};
    }

    public Type getRawType() {
        return mapClass;
    }

    public Type getOwnerType() {
        return null;
    }

}