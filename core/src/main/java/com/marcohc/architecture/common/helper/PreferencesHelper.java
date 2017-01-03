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

/**
 * For managing shared preferences.
 */
public class PreferencesHelper extends PreferencesMethods {

    public PreferencesHelper(Context context) {
        super(context);
    }

    public PreferencesHelper(Context context, String sharedPreferencesName) {
        super(context, sharedPreferencesName);
    }
}
