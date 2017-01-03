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
import android.os.Build;

/**
 * Used for managing shared preferences with encryption.
 */
public class SecurePreferences extends PreferencesMethods {

    public SecurePreferences(Context context) {
        String defaultName = String.format("%s_%s", context.getPackageName(), "preferences");
        String uniqueId = getUniqueId();
        sharedPreferences = new com.securepreferences.SecurePreferences(context, uniqueId, defaultName);
    }

    public static String getUniqueId() {
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
