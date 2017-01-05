package com.marcohc.architecture.parser;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class GenericCollection<Collection, Value> implements ParameterizedType {

    private final Class<Collection> collectionClass;
    private final Class<Value> valueClass;

    public GenericCollection(Class<Collection> collectionClass, Class<Value> valueClass) {
        this.collectionClass = collectionClass;
        this.valueClass = valueClass;
    }

    public Type[] getActualTypeArguments() {
        return new Type[]{valueClass};
    }

    public Type getRawType() {
        return collectionClass;
    }

    public Type getOwnerType() {
        return null;
    }

}