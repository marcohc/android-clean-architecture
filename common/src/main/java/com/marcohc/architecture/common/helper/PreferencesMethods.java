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
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.marcohc.architecture.common.utils.Preconditions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Service use to
 */
public class PreferencesMethods {

    protected SharedPreferences sharedPreferences;

    public PreferencesMethods(Context context, String sharedPreferencesName) {
        sharedPreferences = context.getSharedPreferences(sharedPreferencesName, Context.MODE_PRIVATE);
    }

    public PreferencesMethods(Context context) {
        String defaultName = String.format("%s_%s", context.getPackageName(), "preferences");
        sharedPreferences = context.getSharedPreferences(defaultName, Context.MODE_PRIVATE);
    }

    protected PreferencesMethods() {
        // Used for extension
    }

    public void clear() {
        sharedPreferences.edit().clear().apply();
    }

    public boolean getBoolean(@NonNull String key, boolean defaultValue) {
        return sharedPreferences.getBoolean(Preconditions.checkNotNull(key), defaultValue);
    }

    public float getFloat(@NonNull String key, float defaultValue) {
        return sharedPreferences.getFloat(Preconditions.checkNotNull(key), defaultValue);
    }

    public int getInt(@NonNull String key, int defaultValue) {
        return sharedPreferences.getInt(Preconditions.checkNotNull(key), defaultValue);
    }

    @NonNull
    public Long getLong(@NonNull String key, long defaultValue) {
        return sharedPreferences.getLong(Preconditions.checkNotNull(key), defaultValue);
    }

    @NonNull
    public List<Long> getLongList(@NonNull String key) {
        List<Long> longList = new ArrayList<>();
        Set<String> setValues = sharedPreferences.getStringSet(Preconditions.checkNotNull(key), null);
        if (setValues != null) {
            List<String> stringList = new ArrayList<>();
            stringList.addAll(setValues);
            longList = getLongListFromStringList(stringList);
        }
        return longList;
    }

    @Nullable
    public String getString(@NonNull String key, @Nullable String defaultValue) {
        return sharedPreferences.getString(Preconditions.checkNotNull(key), defaultValue);
    }

    @NonNull
    public List<String> getStringList(@NonNull String key) {
        List<String> stringList = new ArrayList<>();
        Set<String> setValues = sharedPreferences.getStringSet(Preconditions.checkNotNull(key), null);
        if (setValues != null) {
            stringList.addAll(setValues);
        }
        return stringList;
    }

    public void putBoolean(@NonNull String key, boolean value) {
        sharedPreferences.edit().putBoolean(Preconditions.checkNotNull(key), value).apply();
    }

    public void putFloat(@NonNull String key, float value) {
        sharedPreferences.edit().putFloat(Preconditions.checkNotNull(key), value).apply();
    }

    public void putInt(@NonNull String key, int value) {
        sharedPreferences.edit().putInt(Preconditions.checkNotNull(key), value).apply();
    }

    public void putLong(@NonNull String key, long value) {
        sharedPreferences.edit().putLong(Preconditions.checkNotNull(key), value).apply();
    }

    public void putLongList(@NonNull String key, List<Long> values) {
        Set<String> setValues = new HashSet<>(getStringListFromLongList(values));
        sharedPreferences.edit().putStringSet(Preconditions.checkNotNull(key), setValues).apply();
    }

    public void putString(@NonNull String key, @Nullable String value) {
        sharedPreferences.edit().putString(Preconditions.checkNotNull(key), value).apply();
    }

    public void putStringList(@NonNull String key, @NonNull List<String> values) {
        Set<String> setValues = new HashSet<>(Preconditions.checkNotNull(values));
        sharedPreferences.edit().putStringSet(Preconditions.checkNotNull(key), setValues).apply();
    }

    public void remove(@NonNull String key) {
        sharedPreferences.edit().remove(key).apply();
    }

    private static List<String> getStringListFromLongList(@NonNull List<Long> longList) {
        Preconditions.checkNotNull(longList);
        List<String> stringList = new ArrayList<>();
        for (Long item : longList) {
            stringList.add(String.valueOf(item));
        }
        return stringList;
    }

    private static List<Long> getLongListFromStringList(@NonNull List<String> stringList) {
        Preconditions.checkNotNull(stringList);
        List<Long> longList = new ArrayList<>();
        for (String item : stringList) {
            longList.add(Long.valueOf(item));
        }
        return longList;
    }
}
