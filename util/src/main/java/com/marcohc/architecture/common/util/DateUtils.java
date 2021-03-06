/*
 * Copyright (C) 2015 Marco Hernaiz Cao
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

package com.marcohc.architecture.common.util;

import com.marcohc.architecture.common.util.helper.StringUtils;

import java.util.Calendar;
import java.util.Locale;

/**
 * Calendar and date useful methods
 */
public class DateUtils {

    // ************************************************************************************************************************************************************************
    // * Calendar methods
    // ************************************************************************************************************************************************************************

    public static String formatCurrentMonth(Calendar calendar) {
        return StringUtils.getFirstCapitalize(String.format(Locale.getDefault(), "%tB", calendar));
    }

    public static String getCalendarCurrentMonthString() {
        Calendar calendar = Calendar.getInstance();
        return StringUtils.getFirstCapitalize(String.format(Locale.getDefault(), "%tB", calendar));
    }

    public static String getCalendarLastMonthString() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        return StringUtils.getFirstCapitalize(String.format(Locale.getDefault(), "%tB", calendar));
    }

    public static String getCalendarLastThreeMonthsString() {
        String text = "";
        Calendar initCalendar = Calendar.getInstance();
        Calendar endCalendar = getCalendarThreeMonthsAgo();
        endCalendar.add(Calendar.MONTH, 1);
        text = StringUtils.getFirstCapitalize(String.format(Locale.getDefault(), "%tB", endCalendar).substring(0, 3));
        text += " - " + StringUtils.getFirstCapitalize(String.format(Locale.getDefault(), "%tB", initCalendar).substring(0, 3));
        return text;
    }

    public static String getCalendarLastSixMonthsString() {
        String text = "";
        Calendar initCalendar = Calendar.getInstance();
        Calendar endCalendar = getCalendarSixMonthsAgo();
        endCalendar.add(Calendar.MONTH, 1);
        text = StringUtils.getFirstCapitalize(String.format(Locale.getDefault(), "%tB", endCalendar).substring(0, 3));
        text += " - " + StringUtils.getFirstCapitalize(String.format(Locale.getDefault(), "%tB", initCalendar).substring(0, 3));
        return text;
    }

    public static String getCalendarCurrentYearString() {
        String text = "";
        Calendar calendar = Calendar.getInstance();
        text = String.valueOf(calendar.get(Calendar.YEAR));
        return text;
    }

    public static Calendar getCalendarFirstDayCurrentMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        getCalendarInitDay(calendar);
        return calendar;
    }

    public static Calendar getCalendarLastDayCurrentMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        getCalendarEndDay(calendar);
        return calendar;
    }

    public static Calendar getCalendarFirstDayLastMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        getCalendarInitDay(calendar);
        return calendar;
    }

    public static Calendar getCalendarLastDayLastMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        getCalendarEndDay(calendar);
        return calendar;
    }

    public static Calendar getCalendarFirstDayTrimester() {
        Calendar calendar = Calendar.getInstance();
        int month = getMonthTrimester(calendar.get(Calendar.MONTH));
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        getCalendarInitDay(calendar);
        return calendar;
    }

    public static Calendar getCalendarLastDayTrimester() {
        Calendar calendar = Calendar.getInstance();
        int month = getMonthTrimester(calendar.get(Calendar.MONTH));
        calendar.set(Calendar.MONTH, month + 2);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        getCalendarEndDay(calendar);
        return calendar;
    }

    public static Calendar getCalendarThreeMonthsAgo() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -3);
        getCalendarInitDay(calendar);
        return calendar;
    }

    public static Calendar getCalendarSixMonthsAgo() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -6);
        getCalendarInitDay(calendar);
        return calendar;
    }

    public static Calendar getCalendarFirstDayCurrentYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        getCalendarInitDay(calendar);
        return calendar;
    }

    public static Calendar getCalendarLastDayCurrentYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, 11);
        calendar.set(Calendar.DAY_OF_MONTH, 31);
        getCalendarEndDay(calendar);
        return calendar;
    }

    public static Calendar getCalendarInitDay(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar;
    }

    public static Calendar getCalendarEndDay(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar;
    }

    public static Calendar getCalendarFirstDayWeek(Calendar calendar) {
        Calendar calendarToReturn = Calendar.getInstance();
        calendarToReturn.setTime(calendar.getTime());
        calendarToReturn.set(Calendar.DAY_OF_WEEK, calendarToReturn.getFirstDayOfWeek());
        getCalendarInitDay(calendarToReturn);
        return calendarToReturn;
    }

    public static Calendar getCalendarLastDayWeek(Calendar calendar) {
        Calendar calendarToReturn = getCalendarFirstDayWeek(calendar);
        calendarToReturn.add(Calendar.DAY_OF_MONTH, 7);
        return calendarToReturn;
    }

    private static int getMonthTrimester(int month) {
        return 3 * ((month - 1) / 3) + 1;
    }

    public static boolean isBeforeCurrentDate(long recordScheduledTime) {
        Calendar calendar = Calendar.getInstance();
        Calendar auxCalendar = Calendar.getInstance();
        auxCalendar.setTimeInMillis(recordScheduledTime);
        return auxCalendar.before(calendar);
    }

    public static boolean isInTheSameDayOfCurrentDate(long time) {
        Calendar currentCalendar = Calendar.getInstance();
        Calendar lastUseCalendar = Calendar.getInstance();
        lastUseCalendar.setTimeInMillis(time);
        if (lastUseCalendar.get(Calendar.YEAR) == currentCalendar.get(Calendar.YEAR) && lastUseCalendar.get(Calendar.DAY_OF_YEAR) == currentCalendar.get(Calendar.DAY_OF_YEAR)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isInTheLast24HoursOfCurrentDate(long time) {
        Calendar currentCalendar = Calendar.getInstance();
        Calendar last24HoursCalendar = Calendar.getInstance();
        last24HoursCalendar.add(Calendar.HOUR, -24);
        Calendar calendarToCheck = Calendar.getInstance();
        calendarToCheck.setTimeInMillis(time);
        return calendarToCheck.before(currentCalendar) && calendarToCheck.after(last24HoursCalendar);
    }

    public static boolean areInTheSameMonth(Calendar calendarOne, Calendar calendarTwo) {
        if (calendarOne.get(Calendar.YEAR) == calendarTwo.get(Calendar.YEAR) && calendarOne.get(Calendar.MONTH) == calendarTwo.get(Calendar.MONTH)) {
            return true;
        } else {
            return false;
        }
    }
}
