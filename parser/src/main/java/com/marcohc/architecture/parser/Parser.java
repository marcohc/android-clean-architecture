package com.marcohc.architecture.parser;

import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.util.Date;

import timber.log.Timber;

/**
 * Singleton for serialization using Gson.
 *
 * @author Marco Hernaiz
 * @since 09/08/16
 */
public final class Parser {

    private static final String ISO_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";
    private static final GsonBuilder gsonBuilder;
    private static final Gson gson;

    static {
        gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Date.class, new DateDeserializer(ISO_FORMAT));
        gson = gsonBuilder.create();
    }

    private Parser() {
    }

    /**
     * Parse the object into a specific type.
     *
     * @param jsonObject the object to parse
     * @param clazz the class you want to transform to
     * @param <T> the type of your class
     * @return the object transformed to your type or null in case of error
     */
    @Nullable
    public static <T> T parse(Object jsonObject, Class<T> clazz) {
        T object = null;
        try {
            if (jsonObject != null && clazz != null) {
                String jsonString = getJsonString(jsonObject);
                object = gson.fromJson(jsonString, clazz);
            }
        } catch (JsonIOException e) {
            Timber.e("parse: JsonIOException: %s", e.getMessage());
        } catch (JsonSyntaxException e) {
            Timber.e("parse: JsonSyntaxException: %s", e.getMessage());
        } catch (IllegalStateException e) {
            Timber.e("parse: IllegalStateException: %s", e.getMessage());
        }
        return object;
    }

    /**
     * To modify configurations.
     *
     * @return GsonBuilder
     */
    public static GsonBuilder getGsonBuilder() {
        return gsonBuilder;
    }

    /**
     * To modify configurations.
     *
     * @return Gson
     */
    public static Gson getGson() {
        return gson;
    }

    private static String getJsonString(Object jsonObject) {
        String jsonString;
        if (!String.class.isInstance(jsonObject)) {
            jsonString = gson.toJson(jsonObject);
        } else {
            jsonString = (String) jsonObject;
        }
        return jsonString;
    }

    /**
     * Parse the object into a specific type using a {@link TypeToken}.
     *
     * @param jsonObject the object to parse
     * @param typeToken the type token with the class you want to transform to
     * @param <T> the type of your class
     * @return the object transformed to your type or null in case of error
     */
    @Nullable
    public static <T> T parse(Object jsonObject, TypeToken<T> typeToken) {
        T object = null;
        try {
            if (jsonObject != null && typeToken != null) {
                String jsonString = getJsonString(jsonObject);
                object = gson.fromJson(jsonString, typeToken.getType());
            }
        } catch (JsonIOException e) {
            Timber.e("parse: JsonIOException: %s", e.getMessage());
        } catch (JsonSyntaxException e) {
            Timber.e("parse: JsonSyntaxException: %s", e.getMessage());
        } catch (IllegalStateException e) {
            Timber.e("parse: IllegalStateException: %s", e.getMessage());
        }
        return object;
    }

    /**
     * Parse the object into a specific type using a {@link GenericCollection}.
     *
     * @param jsonObject the object to parse
     * @param genericCollection the generic collection
     * @param <T> the type of your class
     * @param <Collection> the type of collection you want
     * @param <Value> the type of the children of this collection
     * @return the object transformed to your type or null in case of error
     */
    @Nullable
    public static <T, Collection, Value> T parseCollection(Object jsonObject, GenericCollection<Collection, Value> genericCollection) {
        T object = null;
        try {
            if (jsonObject != null && genericCollection != null) {
                String jsonString = getJsonString(jsonObject);
                object = gson.fromJson(jsonString, genericCollection);
            }
        } catch (JsonIOException e) {
            Timber.e("parse: JsonIOException: %s", e.getMessage());
        } catch (JsonSyntaxException e) {
            Timber.e("parse: JsonSyntaxException: %s", e.getMessage());
        } catch (IllegalStateException e) {
            Timber.e("parse: IllegalStateException: %s", e.getMessage());
        }
        return object;
    }

    /**
     * Parse the object into a specific type using a {@link GenericMap}.
     *
     * @param jsonObject the object to parse
     * @param genericCollection the generic collection
     * @param <T> the type of your class
     * @param <Map> the type of collection you want
     * @param <Key> the key type of your map
     * @param <Value> the type of the children of this collection
     * @return the object transformed to your type or null in case of error
     */
    @Nullable
    public static <T, Map, Key, Value> T parseMap(Object jsonObject, GenericMap<Map, Key, Value> genericCollection) {
        T object = null;
        try {
            if (jsonObject != null && genericCollection != null) {
                String jsonString = getJsonString(jsonObject);
                object = gson.fromJson(jsonString, genericCollection);
            }
        } catch (JsonIOException e) {
            Timber.e("parse: JsonIOException: %s", e.getMessage());
        } catch (JsonSyntaxException e) {
            Timber.e("parse: JsonSyntaxException: %s", e.getMessage());
        } catch (IllegalStateException e) {
            Timber.e("parse: IllegalStateException: %s", e.getMessage());
        }
        return object;
    }

    /**
     * Converts the object to a json String representation.
     *
     * @param jsonObject the object
     * @return the json String
     */
    @Nullable
    public static String toJsonString(Object jsonObject) {
        String jsonString = "";
        if (jsonObject != null) {
            try {
                jsonString = gson.toJson(jsonObject);
            } catch (JsonIOException e) {
                Timber.e("toJsonString: %s", e.getMessage());
            }
        }
        return jsonString;
    }
}
