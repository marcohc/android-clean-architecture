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

import android.Manifest;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.annotation.RequiresPermission;

/**
 * Class to retrieve information from user or device
 */
public class AppInfoHelper {

    private static final String IS_FIRST_APP_EXECUTION = "is_first_app_start";
    private static final String LAST_APP_EXECUTION = "last_app_execution";

    // ************************************************************************************************************************************************************************
    // * User behaviour methods
    // ************************************************************************************************************************************************************************

    public static boolean isFirstAppExecution() {
        String firstAppStartValue = PreferencesHelper.getInstance().getString(IS_FIRST_APP_EXECUTION, null);
        boolean isFirstAppStart;
        if (firstAppStartValue == null || firstAppStartValue.equals("true")) {
            isFirstAppStart = true;
            PreferencesHelper.getInstance().putString(IS_FIRST_APP_EXECUTION, "false");
        } else {
            isFirstAppStart = false;
            PreferencesHelper.getInstance().putString(IS_FIRST_APP_EXECUTION, "false");
        }
        return isFirstAppStart;
    }

    public static void forceFirstAppExecution() {
        PreferencesHelper.getInstance().putString(IS_FIRST_APP_EXECUTION, "true");
    }

    public static void trackLastAppExecution() {
        PreferencesHelper.getInstance().putLong(LAST_APP_EXECUTION, System.currentTimeMillis());
    }

    public static boolean isFirstUseToday() {
        long lastUseTime = PreferencesHelper.getInstance().getLong(LAST_APP_EXECUTION, -1L);
        return lastUseTime == -1 || !DateHelper.isInTheSameDayOfCurrentDate(lastUseTime);
    }

    public static boolean isFirstUseLast24Hours() {
        long lastUseTime = PreferencesHelper.getInstance().getLong(LAST_APP_EXECUTION, -1L);
        return lastUseTime == -1 || !DateHelper.isInTheLast24HoursOfCurrentDate(lastUseTime);
    }

    public static String getUniqueId() {
        try {
            return "35" + //we make this look like a valid IMEI
                    Build.BOARD.length() % 10 + Build.BRAND.length() % 10 +
                    Build.CPU_ABI.length() % 10 + Build.DEVICE.length() % 10 +
                    Build.DISPLAY.length() % 10 + Build.HOST.length() % 10 +
                    Build.ID.length() % 10 + Build.MANUFACTURER.length() % 10 +
                    Build.MODEL.length() % 10 + Build.PRODUCT.length() % 10 +
                    Build.TAGS.length() % 10 + Build.TYPE.length() % 10 +
                    Build.USER.length() % 10; //13 digits
        } catch (Exception ignored) {
            return "1234";
        }
    }

    @SuppressWarnings("deprecation")
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    public static boolean isConnectedToWifi(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mWifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        return mWifi.isConnected();
    }

    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    public static boolean hasInternetConnection(Context context) {
        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected() && networkInfo.isAvailable();
    }

}
