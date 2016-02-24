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
package com.marcohc.helperoid;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
        } catch (IOException var4) {
            Timber.e("ParserHelper", String.format("parse: %s", var4.getMessage()));
        }
        return object;
    }

    public <T> T parse(Object value, TypeReference<T> valueType) {
        T object = null;
        try {
            if (value != null && valueType != null) {
                String e = objectMapper.writeValueAsString(value);
                object = objectMapper.readValue(e, valueType);
            }
        } catch (IOException e) {
            Timber.e(String.format("parse: %s", e.getMessage()));
        }
        return object;
    }

    public <T> List<T> parseJsonArray(JSONArray jsonArray, Class<T> type) {
        List<T> list = new ArrayList<>();
        if (jsonArray != null) {
            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    list.add(parse(jsonArray.get(i).toString(), type));
                } catch (JSONException e) {
                    Timber.e(String.format("parseJsonStringArray: %s", e.getMessage()));
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
            } catch (JSONException e) {
                Timber.e(String.format("parseJsonStringArray: %s", e.getMessage()));
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
                Timber.e(String.format("toJsonString: %s", e.getMessage()));
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