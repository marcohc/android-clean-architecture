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
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.marcohc.architecture.common.service.preference.PreferencesService;
import com.marcohc.architecture.common.utils.Preconditions;

/**
 * Used for managing shared preferences with encryption.
 */
public class SecurePreferencesServiceImpl implements PreferencesService {

    private final SharedPreferences sharedPreferences;

    public SecurePreferencesServiceImpl(Context context) {
        String defaultName = String.format("%s_%s", context.getPackageName(), "preferences");
        String uniqueId = getUniqueId();
        sharedPreferences = new com.securepreferences.SecurePreferences(context, uniqueId, defaultName);
    }

    @Override
    public void clear() {
        sharedPreferences.edit().clear().apply();
    }

    @Override
    public boolean getBoolean(@NonNull String key, boolean defaultValue) {
        return sharedPreferences.getBoolean(Preconditions.checkNotNull(key), defaultValue);
    }

    @Override
    public float getFloat(@NonNull String key, float defaultValue) {
        return sharedPreferences.getFloat(Preconditions.checkNotNull(key), defaultValue);
    }

    @Override
    public int getInt(@NonNull String key, int defaultValue) {
        return sharedPreferences.getInt(Preconditions.checkNotNull(key), defaultValue);
    }

    @Override
    public long getLong(@NonNull String key, long defaultValue) {
        return sharedPreferences.getLong(Preconditions.checkNotNull(key), defaultValue);
    }

    @Override
    @Nullable
    public String getString(@NonNull String key, @Nullable String defaultValue) {
        return sharedPreferences.getString(Preconditions.checkNotNull(key), defaultValue);
    }

    @Override
    public void putBoolean(@NonNull String key, boolean value) {
        sharedPreferences.edit().putBoolean(Preconditions.checkNotNull(key), value).apply();
    }

    @Override
    public void putFloat(@NonNull String key, float value) {
        sharedPreferences.edit().putFloat(Preconditions.checkNotNull(key), value).apply();
    }

    @Override
    public void putInt(@NonNull String key, int value) {
        sharedPreferences.edit().putInt(Preconditions.checkNotNull(key), value).apply();
    }

    @Override
    public void putLong(@NonNull String key, long value) {
        sharedPreferences.edit().putLong(Preconditions.checkNotNull(key), value).apply();
    }

    @Override
    public void putString(@NonNull String key, @Nullable String value) {
        sharedPreferences.edit().putString(Preconditions.checkNotNull(key), value).apply();
    }

    @Override
    public void remove(@NonNull String key) {
        sharedPreferences.edit().remove(key).apply();
    }

    private static String getUniqueId() {
        try {
            // We make this look like a valid IMEI
            return "35" +
                    Build.BOARD.length() % 10 + Build.BRAND.length() % 10
                    + Build.DEVICE.length() % 10 + Build.DISPLAY.length() % 10
                    + Build.HOST.length() % 10 + Build.ID.length() % 10
                    + Build.MANUFACTURER.length() % 10 + Build.MODEL.length() % 10
                    + Build.PRODUCT.length() % 10 + Build.TAGS.length() % 10
                    + Build.TYPE.length() % 10 + Build.USER.length() % 10; //13 digits
        } catch (Exception ignored) {
            return "1234";
        }
    }
}
