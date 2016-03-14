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

import com.securepreferences.SecurePreferences;


/**
 * For managing shared preferences with encryption
 * <p/>
 * Call {@link #setUp(Context)} method first or {@link #setUp(Context, String)} first
 * <p/>
 * Default shared preferences name will be: <package_name>_preferences
 */
public class SecurePreferencesHelper extends PreferencesMethods {

    // ************************************************************************************************************************************************************************
    // * Attributes
    // ************************************************************************************************************************************************************************

    protected static SecurePreferencesHelper instance;

    // ************************************************************************************************************************************************************************
    // * Constructor
    // ************************************************************************************************************************************************************************

    private SecurePreferencesHelper(Context context, String uniqueId) {
        String defaultName = String.format("%s_%s", context.getPackageName(), "preferences");
        sharedPreferences = new SecurePreferences(context, uniqueId, defaultName);
    }

    private SecurePreferencesHelper(Context context, String sharedPreferencesName, String uniqueId) {
        sharedPreferences = new SecurePreferences(context, uniqueId, sharedPreferencesName);
    }

    // ************************************************************************************************************************************************************************
    // * Initialization
    // ************************************************************************************************************************************************************************

    public static void setUp(Context context) {
        if (context == null) {
            throw new PreferencesException("Context must not be null!");
        }
        instance = new SecurePreferencesHelper(context, AppInfoHelper.getUniqueId());
    }

    public static void setUp(Context context, String sharedPreferencesName) {
        if (context == null) {
            throw new PreferencesException("Context must not be null!");
        }
        instance = new SecurePreferencesHelper(context, sharedPreferencesName, AppInfoHelper.getUniqueId());
    }

    // ************************************************************************************************************************************************************************
    // * Initialization methods
    // ************************************************************************************************************************************************************************

    public static SecurePreferencesHelper getInstance() {
        if (instance == null) {
            throw new PreferencesException("setUp(Context context) must be called first!");
        }
        return instance;
    }

    public static void clearInstance() {
        instance.sharedPreferences = null;
        instance = null;
    }
}
