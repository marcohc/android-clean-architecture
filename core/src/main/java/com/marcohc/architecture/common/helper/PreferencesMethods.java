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

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * For extend from PreferencesHelper classes
 * <p>
 * Call {@link #setUp(Context)} method first or {@link #setUp(Context, String)} first
 * <p>
 * Default shared preferences name will be: <package_name>_preferences
 */
public class PreferencesMethods {

    // ************************************************************************************************************************************************************************
    // * Attributes
    // ************************************************************************************************************************************************************************

    protected SharedPreferences sharedPreferences;

    // ************************************************************************************************************************************************************************
    // * Public methods
    // ************************************************************************************************************************************************************************

    public void remove(String key) {
        sharedPreferences.edit().remove(key).apply();
    }

    public void clear() {
        sharedPreferences.edit().clear().apply();
    }

    public String getString(String key, String defaultValue) {
        return sharedPreferences.getString(key, defaultValue);
    }

    public void putString(String key, String value) {
        sharedPreferences.edit().putString(key, value).apply();
    }

    public Boolean getBoolean(String key, Boolean defaultValue) {
        return sharedPreferences.getBoolean(key, defaultValue);
    }

    public void putBoolean(String key, Boolean value) {
        sharedPreferences.edit().putBoolean(key, value).apply();
    }

    public Long getLong(String key, Long defaultValue) {
        return sharedPreferences.getLong(key, defaultValue);
    }

    public void putLong(String key, Long value) {
        sharedPreferences.edit().putLong(key, value).apply();
    }

    public Integer getInt(String key, Integer defaultValue) {
        return sharedPreferences.getInt(key, defaultValue);
    }

    public void putInt(String key, Integer value) {
        sharedPreferences.edit().putInt(key, value).apply();
    }

    public void putStringList(String key, List<String> values) {
        Set<String> setValues = new HashSet<>(values);
        sharedPreferences.edit().putStringSet(key, setValues).apply();
    }

    public List<String> getStringList(String key) {
        List<String> stringList = new ArrayList<>();
        Set<String> setValues = sharedPreferences.getStringSet(key, null);
        if (setValues != null) {
            stringList.addAll(setValues);
        }
        return stringList;
    }

    public void putLongList(String key, List<Long> values) {
        Set<String> setValues = new HashSet<>(getStringListFromLongList(values));
        sharedPreferences.edit().putStringSet(key, setValues).apply();
    }

    public List<Long> getLongList(String key) {
        List<Long> longList = new ArrayList<>();
        Set<String> setValues = sharedPreferences.getStringSet(key, null);
        if (setValues != null) {
            List<String> stringList = new ArrayList<>();
            stringList.addAll(setValues);
            longList = getLongListFromStringList(stringList);
        }
        return longList;
    }

    // ************************************************************************************************************************************************************************
    // * Auxiliary methods
    // ************************************************************************************************************************************************************************

    private List<Long> getLongListFromStringList(List<String> stringList) {
        List<Long> longList = new ArrayList<>();
        for (String item : stringList) {
            longList.add(Long.valueOf(item));
        }
        return longList;
    }

    private List<String> getStringListFromLongList(List<Long> longList) {
        List<String> stringList = new ArrayList<>();
        for (Long item : longList) {
            stringList.add(String.valueOf(item));
        }
        return stringList;
    }

    // ************************************************************************************************************************************************************************
    // * Initialization exception
    // ************************************************************************************************************************************************************************

    public static class PreferencesException extends RuntimeException {
        public PreferencesException(String s) {
        }
    }
}
