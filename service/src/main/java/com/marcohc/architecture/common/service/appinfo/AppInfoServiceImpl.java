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
package com.marcohc.architecture.common.service.appinfo;

import com.marcohc.architecture.common.service.preferences.PreferencesService;

import java.util.Calendar;

/**
 * Class to retrieve information from user or device.
 */
public class AppInfoServiceImpl implements AppInfoService {

    private static final String IS_FIRST_APP_EXECUTION = "is_first_app_start";
    private static final String LAST_APP_EXECUTION = "last_app_execution";
    private final PreferencesService mPreferenceService;

    public AppInfoServiceImpl(PreferencesService preferenceService) {
        mPreferenceService = preferenceService;
    }

    @Override
    public void forceFirstAppExecution() {
        mPreferenceService.putBoolean(IS_FIRST_APP_EXECUTION, true);
    }

    @Override
    public boolean isFirstAppExecution() {
        boolean isFirstAppStart = mPreferenceService.getBoolean(IS_FIRST_APP_EXECUTION, true);
        mPreferenceService.putBoolean(IS_FIRST_APP_EXECUTION, false);
        return isFirstAppStart;
    }

    @Override
    public boolean isFirstUseLast24Hours() {
        long lastUseTime = mPreferenceService.getLong(LAST_APP_EXECUTION, -1L);
        return lastUseTime == -1 || !isInTheLast24HoursOfCurrentDate(lastUseTime);
    }

    @Override
    public boolean isFirstUseToday() {
        long lastUseTime = mPreferenceService.getLong(LAST_APP_EXECUTION, -1L);
        return lastUseTime == -1 || !isInTheSameDayOfCurrentDate(lastUseTime);
    }

    @Override
    public void trackLastAppExecution() {
        mPreferenceService.putLong(LAST_APP_EXECUTION, System.currentTimeMillis());
    }

    private static boolean isInTheLast24HoursOfCurrentDate(long time) {
        Calendar currentCalendar = Calendar.getInstance();
        Calendar last24HoursCalendar = Calendar.getInstance();
        last24HoursCalendar.add(Calendar.HOUR, -24);
        Calendar calendarToCheck = Calendar.getInstance();
        calendarToCheck.setTimeInMillis(time);
        return calendarToCheck.before(currentCalendar) && calendarToCheck.after(last24HoursCalendar);
    }

    private static boolean isInTheSameDayOfCurrentDate(long time) {
        Calendar currentCalendar = Calendar.getInstance();
        Calendar lastUseCalendar = Calendar.getInstance();
        lastUseCalendar.setTimeInMillis(time);
        if (lastUseCalendar.get(Calendar.YEAR) == currentCalendar.get(Calendar.YEAR) && lastUseCalendar.get(Calendar.DAY_OF_YEAR) == currentCalendar.get(Calendar.DAY_OF_YEAR)) {
            return true;
        } else {
            return false;
        }
    }
}
