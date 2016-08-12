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

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import timber.log.Timber;

/**
 * Singleton for parsing useful methods
 */
public class ParserHelper {

    private static ParserHelper instance;

    private ObjectMapper objectMapper;

    private ParserHelper() {
        objectMapper = new ObjectMapper();
    }

    // ************************************************************************************************************************************************************************
    // * Public methods
    // ************************************************************************************************************************************************************************

    public static ParserHelper getInstance() {
        if (instance == null) {
            instance = new ParserHelper();
        }
        return instance;
    }

    public <T> T parse(Object value, Class<T> valueType) {
        T object = null;
        try {
            if (value != null && valueType != null) {
                if (!String.class.isInstance(value)) {
                    value = objectMapper.writeValueAsString(value);
                }
                object = objectMapper.readValue((String) value, valueType);
            }
        } catch (Exception e) {
            Timber.e("parse: %s", e.getMessage());
        }
        return object;
    }

    public <T> T parse(Object value, TypeReference<T> valueType) {
        T object = null;
        try {
            if (value != null && valueType != null) {
                if (!String.class.isInstance(value)) {
                    value = this.objectMapper.writeValueAsString(value);
                }
                object = objectMapper.readValue((String) value, valueType);
            }
        } catch (Exception e) {
            Timber.e("parse: %s", e.getMessage());
        }
        return object;
    }

    public <T> T parse(Object value, Class<? extends Map> mapClass, Class<?> keyClass, Class<?> valueClass) {
        T object = null;
        try {
            if (value != null && mapClass != null && keyClass != null && valueClass != null) {
                if (!String.class.isInstance(value)) {
                    value = this.objectMapper.writeValueAsString(value);
                }
                JavaType javaType = objectMapper.getTypeFactory().constructMapType(mapClass, keyClass, valueClass);
                object = objectMapper.readValue((String) value, javaType);
            }
        } catch (Exception e) {
            Timber.e("parse: %s", e.getMessage());
        }
        return object;
    }

    public <T> List<T> parseJsonArray(JSONArray jsonArray, Class<T> type) {
        List<T> list = new ArrayList<>();
        if (jsonArray != null) {
            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    list.add(parse(jsonArray.get(i).toString(), type));
                } catch (Exception e) {
                    Timber.e("parseJsonStringArray: %s", e.getMessage());
                }
            }
        }
        return list;
    }

    public <T> List<T> parseJsonStringArray(String jsonString, Class<T> type) {
        List<T> list = new ArrayList<>();
        if (jsonString != null) {
            try {
                list = parseJsonArray(new JSONArray(jsonString), type);
            } catch (Exception e) {
                Timber.e("parseJsonStringArray: %s", e.getMessage());
            }
        }
        return list;
    }

    public String toJsonString(Object jsonObject) {
        String jsonString = "";
        if (jsonObject != null) {
            try {
                jsonString = objectMapper.writeValueAsString(jsonObject);
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
    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }
}