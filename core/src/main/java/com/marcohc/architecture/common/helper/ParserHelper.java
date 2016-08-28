/*
 * Copyright (C) 2016 Marco Hernaiz Cao
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.marcohc.architecture.common.helper;

import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.Date;

import timber.log.Timber;

/**
 * Singleton for parsing useful methods
 */
public class ParserHelper {

    // ************************************************************************************************************************************************************************
    // * Constants
    // ************************************************************************************************************************************************************************

    private static final String ISO_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";

    // ************************************************************************************************************************************************************************
    // * Attributes
    // ************************************************************************************************************************************************************************

    private final GsonBuilder gsonBuilder;
    private final Gson gson;

    // ************************************************************************************************************************************************************************
    // * Constructors
    // ************************************************************************************************************************************************************************

    private ParserHelper() {
        gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Date.class, new DateDeserializer(ISO_FORMAT));
        gson = gsonBuilder.create();
    }

    private static ParserHelper instance;

    public static ParserHelper getInstance() {
        if (instance == null) {
            instance = new ParserHelper();
        }
        return instance;
    }


    // ************************************************************************************************************************************************************************
    // * Public methods
    // ************************************************************************************************************************************************************************

    @Nullable
    public <T> T parse(Object jsonObject, Class<T> clazz) {
        T object = null;
        try {
            if (jsonObject != null && clazz != null) {
                // Convert to json string first
                if (!String.class.isInstance(jsonObject)) {
                    jsonObject = gson.toJson(jsonObject);
                }
                object = gson.fromJson((String) jsonObject, clazz);
            }
        } catch (Exception e) {
            Timber.e("parse: %s", e.getMessage());
        }
        return object;
    }

    @Nullable
    public <T> T parse(Object jsonObject, TypeToken<T> typeToken) {
        T object = null;
        try {
            if (jsonObject != null && typeToken != null) {
                // Convert to json string first
                if (!String.class.isInstance(jsonObject)) {
                    jsonObject = gson.toJson(jsonObject);
                }
                object = gson.fromJson((String) jsonObject, typeToken.getType());
            }
        } catch (Exception e) {
            Timber.e("parse: %s", e.getMessage());
        }
        return object;
    }

    @Nullable
    public <T, Collection, Value> T parseCollection(Object jsonObject, GenericCollection<Collection, Value> genericCollection) {
        T object = null;
        try {
            if (jsonObject != null && genericCollection != null) {
                // Convert to json string first
                if (!String.class.isInstance(jsonObject)) {
                    jsonObject = gson.toJson(jsonObject);
                }
                object = gson.fromJson((String) jsonObject, genericCollection);
            }
        } catch (Exception e) {
            Timber.e("parse: %s", e.getMessage());
        }
        return object;
    }

    @Nullable
    public <T, Map, Key, Value> T parseMap(Object jsonObject, GenericMap<Map, Key, Value> genericCollection) {
        T object = null;
        try {
            if (jsonObject != null && genericCollection != null) {
                // Convert to json string first
                if (!String.class.isInstance(jsonObject)) {
                    jsonObject = gson.toJson(jsonObject);
                }
                object = gson.fromJson((String) jsonObject, genericCollection);
            }
        } catch (Exception e) {
            Timber.e("parse: %s", e.getMessage());
        }
        return object;
    }

    @Nullable
    public String toJsonString(Object jsonObject) {
        String jsonString = "";
        if (jsonObject != null) {
            try {
                jsonString = gson.toJson(jsonObject);
            } catch (Exception e) {
                Timber.e("toJsonString: %s", e.getMessage());
            }
        }
        return jsonString;
    }

    /**
     * To modify configurations
     *
     * @return ObjectMapper
     */
    public GsonBuilder getGsonBuilder() {
        return gsonBuilder;
    }
}