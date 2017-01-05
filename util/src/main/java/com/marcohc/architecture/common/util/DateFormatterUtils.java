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

package com.marcohc.architecture.common.util;

import android.annotation.SuppressLint;
import android.content.Context;

import com.marcohc.architecture.common.util.helper.StringUtils;

import java.io.IOException;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static com.marcohc.architecture.common.util.helper.StringUtils.getFirstCapitalize;

/**
 * Standard date and string formats
 */
@SuppressLint({"DefaultLocale", "SimpleDateFormat"})
public class DateFormatterUtils {

    /**
     * Format depending on Locale like: Tuesday 15
     *
     * @param date
     * @return
     */
    public static String formatDayDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE d");
        return getFirstCapitalize(simpleDateFormat.format(date));
    }

    /**
     * Format depending on Locale like: 11/25/2014
     *
     * @param date
     * @return
     */
    public static String formatShortDate(Date date) {
        DateFormat dateFormatter = DateFormat.getDateInstance(DateFormat.SHORT, Locale.getDefault());
        return dateFormatter.format(date);
    }

    /**
     * Format depending on Locale like: Nov 5, 2014 or 05/11/2014
     *
     * @param date
     * @return
     */
    public static String formatMediumDate(Date date) {
        DateFormat dateFormatter = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault());
        return dateFormatter.format(date);
    }

    /**
     * Format like: Tue 28, 17:05
     *
     * @param date
     * @return
     */
    public static String formatWithWeekDayAndHour(Date date, Context context) {
        SimpleDateFormat simpleDateFormat;
        if (android.text.format.DateFormat.is24HourFormat(context)) {
            simpleDateFormat = new SimpleDateFormat("E d, HH:mm");
        } else {
            simpleDateFormat = new SimpleDateFormat("E d, hh:mm a");
        }
        return StringUtils.getFirstCapitalize(simpleDateFormat.format(date));
    }

    /**
     * Format like: 24/11/2014 - 23:51
     *
     * @param date
     * @return
     */
    public static String formatWithDateAndTime(Date date, Context context) {
        String dateFormatted = formatShortDate(date);
        dateFormatted += " - " + formatHoursAndMinutes(date, context);
        return dateFormatted;
    }

    /**
     * Format like: 17:05
     *
     * @param date
     * @param context
     * @return
     */
    public static String formatHoursAndMinutes(Date date, Context context) {
        SimpleDateFormat simpleDateFormat;
        if (android.text.format.DateFormat.is24HourFormat(context)) {
            simpleDateFormat = new SimpleDateFormat("HH:mm");
        } else {
            simpleDateFormat = new SimpleDateFormat("hh:mm a");
        }
        return simpleDateFormat.format(date);
    }

    public static Date parseDate(String date) throws ParseException {
        DateFormat dateFormatter;
        Date dateReturned;
        try {
            // Try with this format
            dateFormatter = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault());
            dateReturned = dateFormatter.parse(date);
        } catch (ParseException e1) {
            // Else with this other format
            dateFormatter = DateFormat.getDateInstance(DateFormat.SHORT, Locale.getDefault());
            dateReturned = dateFormatter.parse(date);
        }
        return dateReturned;
    }

    /**
     * Formats accepted: dd/MM/YYYY - hh:mm or
     *
     * @param fullDateString
     * @return
     */
    public static Date parseDateWithDateAndTime(String fullDateString) throws Exception {
        Date dateToReturn;
        try {
            dateToReturn = getCalendarFromDateString(fullDateString).getTime();
        } catch (Exception e) {
            dateToReturn = parseDate(fullDateString);
        }
        return dateToReturn;
    }

    private static Calendar getCalendarFromDateString(String fullDateString) throws Exception {
        Calendar calendar;
        String dateString = fullDateString.substring(0, fullDateString.indexOf("-") - 1);
        Date date = parseDate(dateString);
        String timeString = fullDateString.substring(fullDateString.indexOf("-") + 2, fullDateString.length());
        int hours = Integer.valueOf(timeString.substring(0, timeString.indexOf(":")));
        int minutes = Integer.valueOf(timeString.substring(timeString.indexOf(":") + 1, timeString.indexOf(":") + 3));
        String _24HourFormat = null;
        try {
            _24HourFormat = timeString.substring(timeString.indexOf(":") + 4, timeString.length());
        } catch (Exception e) {
        }

        // Differ if hour is in am or pm
        calendar = Calendar.getInstance(Locale.getDefault());
        calendar.setTime(date);
        if (_24HourFormat != null && !_24HourFormat.isEmpty()) {
            if (_24HourFormat.equals("AM")) {
                calendar.set(Calendar.HOUR, hours);
                calendar.set(Calendar.AM_PM, Calendar.AM);
            } else if (_24HourFormat.equals("PM")) {
                calendar.set(Calendar.HOUR, hours);
                calendar.set(Calendar.AM_PM, Calendar.PM);
            } else {
                throw new IOException("24 hour format not valid");
            }
        } else {
            calendar.set(Calendar.HOUR_OF_DAY, hours);
        }
        calendar.set(Calendar.MINUTE, minutes);
        return calendar;
    }

    public static String formatFileSize(long size) {
        if (size <= 0) {
            return "0";
        }
        final String[] units = new String[]{"B", "KB", "MB", "GB", "TB"};
        int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
        return new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
    }

    public static String[] getMonthsStringArray() {
        String[] months = new DateFormatSymbols().getMonths();
        int i = 0;
        for (String month : months) {
            months[i++] = month.substring(0, 1).toUpperCase();
        }
        return months;
    }

}
