package com.marcohc.architecture.common.helper;

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
public final class ParserHelper {

    // ************************************************************************************************************************************************************************
    // * Constants
    // ************************************************************************************************************************************************************************

    private static final String ISO_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";

    // ************************************************************************************************************************************************************************
    // * Attributes
    // ************************************************************************************************************************************************************************

    private static ParserHelper sInstance;
    private final GsonBuilder mGsonBuilder;
    private final Gson mGson;

    // ************************************************************************************************************************************************************************
    // * Constructors
    // ************************************************************************************************************************************************************************

    private ParserHelper() {
        mGsonBuilder = new GsonBuilder();
        mGsonBuilder.registerTypeAdapter(Date.class, new DateDeserializer(ISO_FORMAT));
        mGson = mGsonBuilder.create();
    }

    /**
     * Get singleton instance for this class.
     *
     * @return the instance
     */
    public synchronized static ParserHelper getInstance() {
        if (sInstance == null) {
            sInstance = new ParserHelper();
        }
        return sInstance;
    }

    // ************************************************************************************************************************************************************************
    // * Public methods
    // ************************************************************************************************************************************************************************

    /**
     * Parse the object into a specific type.
     *
     * @param jsonObject the object to parse
     * @param clazz      the class you want to transform to
     * @param <T>        the type of your class
     * @return the object transformed to your type or null in case of error
     */
    @Nullable
    public <T> T parse(Object jsonObject, Class<T> clazz) {
        T object = null;
        try {
            if (jsonObject != null && clazz != null) {
                String jsonString = getJsonString(jsonObject);
                object = mGson.fromJson(jsonString, clazz);
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
     * Parse the object into a specific type using a {@link TypeToken}.
     *
     * @param jsonObject the object to parse
     * @param typeToken  the type token with the class you want to transform to
     * @param <T>        the type of your class
     * @return the object transformed to your type or null in case of error
     */
    @Nullable
    public <T> T parse(Object jsonObject, TypeToken<T> typeToken) {
        T object = null;
        try {
            if (jsonObject != null && typeToken != null) {
                String jsonString = getJsonString(jsonObject);
                object = mGson.fromJson(jsonString, typeToken.getType());
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
     * @param jsonObject        the object to parse
     * @param genericCollection the generic collection
     * @param <T>               the type of your class
     * @param <Collection>      the type of collection you want
     * @param <Value>           the type of the children of this collection
     * @return the object transformed to your type or null in case of error
     */
    @Nullable
    public <T, Collection, Value> T parseCollection(Object jsonObject, GenericCollection<Collection, Value> genericCollection) {
        T object = null;
        try {
            if (jsonObject != null && genericCollection != null) {
                String jsonString = getJsonString(jsonObject);
                object = mGson.fromJson(jsonString, genericCollection);
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
     * @param jsonObject        the object to parse
     * @param genericCollection the generic collection
     * @param <T>               the type of your class
     * @param <Map>             the type of collection you want
     * @param <Key>             the key type of your map
     * @param <Value>           the type of the children of this collection
     * @return the object transformed to your type or null in case of error
     */
    @Nullable
    public <T, Map, Key, Value> T parseMap(Object jsonObject, GenericMap<Map, Key, Value> genericCollection) {
        T object = null;
        try {
            if (jsonObject != null && genericCollection != null) {
                String jsonString = getJsonString(jsonObject);
                object = mGson.fromJson(jsonString, genericCollection);
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
    public String toJsonString(Object jsonObject) {
        String jsonString = "";
        if (jsonObject != null) {
            try {
                jsonString = mGson.toJson(jsonObject);
            } catch (JsonIOException e) {
                Timber.e("toJsonString: %s", e.getMessage());
            }
        }
        return jsonString;
    }

    /**
     * To modify configurations.
     *
     * @return ObjectMapper
     */
    public GsonBuilder getGsonBuilder() {
        return mGsonBuilder;
    }

    private String getJsonString(Object jsonObject) {
        String jsonString;
        if (!String.class.isInstance(jsonObject)) {
            jsonString = mGson.toJson(jsonObject);
        } else {
            jsonString = (String) jsonObject;
        }
        return jsonString;
    }
}
