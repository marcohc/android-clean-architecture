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

import timber.log.Timber;

/**
 * Class to retrieve information from user or device
 * * <p>
 * Call {@link #setUp(PreferencesMethods)} method first selecting the PreferencesHelper or SecurePreferencesHelper classes
 * <p/>
 */
public class AppInfoHelper {

    // ************************************************************************************************************************************************************************
    // * Constants
    // ************************************************************************************************************************************************************************

    private static final String IS_FIRST_APP_EXECUTION = "is_first_app_start";
    private static final String LAST_APP_EXECUTION = "last_app_execution";

    // ************************************************************************************************************************************************************************
    // * Attributes
    // ************************************************************************************************************************************************************************

    private static AppInfoHelper instance;
    private final PreferencesMethods preferencesMethods;

    // ************************************************************************************************************************************************************************
    // * Initialization
    // ************************************************************************************************************************************************************************

    public static synchronized AppInfoHelper getInstance() {
        if (instance == null) {
            throw new PreferencesMethods.PreferencesException("setUp(Context context) must be called first!");
        }
        return instance;
    }

    public static synchronized void setUp(PreferencesMethods preferencesMethods) {
        if (preferencesMethods == null) {
            throw new PreferencesMethods.PreferencesException("PreferencesMethods must not be null!");
        }
        instance = new AppInfoHelper(preferencesMethods);
    }

    private AppInfoHelper(PreferencesMethods preferencesMethods) {
        this.preferencesMethods = preferencesMethods;
    }

    // ************************************************************************************************************************************************************************
    // * User behaviour methods
    // ************************************************************************************************************************************************************************

    public boolean isFirstAppExecution() {
        String firstAppStartValue = preferencesMethods.getString(IS_FIRST_APP_EXECUTION, null);
        boolean isFirstAppStart;
        if (firstAppStartValue == null || firstAppStartValue.equals("true")) {
            isFirstAppStart = true;
            preferencesMethods.putString(IS_FIRST_APP_EXECUTION, "false");
        } else {
            isFirstAppStart = false;
            preferencesMethods.putString(IS_FIRST_APP_EXECUTION, "false");
        }
        return isFirstAppStart;
    }

    public void forceFirstAppExecution() {
        preferencesMethods.putString(IS_FIRST_APP_EXECUTION, "true");
    }

    public void trackLastAppExecution() {
        preferencesMethods.putLong(LAST_APP_EXECUTION, System.currentTimeMillis());
    }

    public boolean isFirstUseToday() {
        long lastUseTime = preferencesMethods.getLong(LAST_APP_EXECUTION, -1L);
        return lastUseTime == -1 || !DateHelper.isInTheSameDayOfCurrentDate(lastUseTime);
    }

    public boolean isFirstUseLast24Hours() {
        long lastUseTime = preferencesMethods.getLong(LAST_APP_EXECUTION, -1L);
        return lastUseTime == -1 || !DateHelper.isInTheLast24HoursOfCurrentDate(lastUseTime);
    }

    public String getUniqueId() {
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
    public boolean isConnectedToWifi(Context context) {
        if (context != null) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mWifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            return mWifi.isConnected();
        } else {
            Timber.e("isConnectedToWifi: context is null!");
            return false;
        }
    }

    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    public boolean hasInternetConnection(Context context) {
        if (context != null) {
            ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connManager.getActiveNetworkInfo();
            return networkInfo != null && networkInfo.isConnected() && networkInfo.isAvailable();
        } else {
            Timber.e("hasInternetConnection: context is null!");
            return false;
        }
    }

}
