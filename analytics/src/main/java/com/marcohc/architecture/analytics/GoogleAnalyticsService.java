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
package com.marcohc.architecture.analytics;

import android.content.Context;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

/**
 * Class to track analytics events.
 * <p>
 * Set up your own {@link R.string.google_analytics_id}
 */
public class GoogleAnalyticsService implements AnalyticsService {

    private static final int PERIOD_TO_DISPATCH = 1800;
    private static GoogleAnalyticsService instance;
    private Tracker analyticsTracker;

    public GoogleAnalyticsService(Context context, boolean dryRun) {
        GoogleAnalytics analytics = GoogleAnalytics.getInstance(context);
        analytics.setLocalDispatchPeriod(PERIOD_TO_DISPATCH);
        analytics.setDryRun(dryRun);
        analyticsTracker = analytics.newTracker(context.getResources().getString(R.string.google_analytics_id));
        analyticsTracker.enableExceptionReporting(true);
        analyticsTracker.enableAdvertisingIdCollection(false);
        analyticsTracker.enableAutoActivityTracking(true);
    }

    @Override
    public void trackAnalyticsEvent(String screenName, String actionName, String value) {
        try {
            if (analyticsTracker != null) {
                HitBuilders.EventBuilder builder = new HitBuilders.EventBuilder();
                builder.setCategory(screenName);
                builder.setAction(actionName);
                builder.setLabel(value);
                analyticsTracker.setScreenName(screenName);
                analyticsTracker.send(builder.build());
                analyticsTracker.setScreenName(null);
            }
        } catch (Exception ignored) {
        }
    }

    @Override
    public void trackAnalyticsEvent(String screenName, String actionName) {
        trackAnalyticsEvent(screenName, actionName, "");
    }

}