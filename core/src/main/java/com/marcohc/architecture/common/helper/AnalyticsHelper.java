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

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.marcohc.architecture.R;

/**
 * Class to track analytics events
 * <p>
 * Call {@link #setUp(Context, boolean)} method first
 * Set up your own {@link R.string.google_analytics_id}
 */
public class AnalyticsHelper {

    // ************************************************************************************************************************************************************************
    // * Attributes and constants
    // ************************************************************************************************************************************************************************

    private static AnalyticsHelper instance;
    private Tracker analyticsTracker;

    // ************************************************************************************************************************************************************************
    // * Initialization methods
    // ************************************************************************************************************************************************************************

    private AnalyticsHelper(Context context, boolean dryRun) {
        GoogleAnalytics analytics = GoogleAnalytics.getInstance(context);
        analytics.setLocalDispatchPeriod(1800);
        analytics.setDryRun(dryRun);
        analyticsTracker = analytics.newTracker(context.getResources().getString(R.string.google_analytics_id));
        analyticsTracker.enableExceptionReporting(true);
        analyticsTracker.enableAdvertisingIdCollection(false);
        analyticsTracker.enableAutoActivityTracking(true);
    }

    public static AnalyticsHelper getInstance() {
        if (instance == null) {
            throw new AnalyticsHelperException("setUp(Context context, boolean dryRun) must be called first!");
        }
        return instance;
    }

    public static void setUp(Context context, boolean dryRun) {
        if (context == null) {
            throw new AnalyticsHelperException("Context must not be null!");
        }
        instance = new AnalyticsHelper(context, dryRun);
    }

    // ************************************************************************************************************************************************************************
    // * Public methods
    // ************************************************************************************************************************************************************************

    public void trackAnalyticsEvent(String screenName, String actionName, String value) {
        try {
            HitBuilders.EventBuilder builder = new HitBuilders.EventBuilder();
            builder.setCategory(screenName);
            builder.setAction(actionName);
            builder.setLabel(value);
            analyticsTracker.setScreenName(screenName);
            analyticsTracker.send(builder.build());
            analyticsTracker.setScreenName(null);
        } catch (Exception ignored) {
        }
    }

    public void trackAnalyticsEvent(String screenName, String actionName) {
        trackAnalyticsEvent(screenName, actionName, "");
    }

    private static class AnalyticsHelperException extends RuntimeException {
        public AnalyticsHelperException(String s) {
        }
    }
}