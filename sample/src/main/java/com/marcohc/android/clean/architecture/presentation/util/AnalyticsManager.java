package com.marcohc.android.clean.architecture.presentation.util;

import android.content.Context;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.marcohc.android.clean.architecture.sample.R;

public class AnalyticsManager {

    private static Tracker analyticsTracker;

    public static void setUp(Context context) {
        GoogleAnalytics analytics = GoogleAnalytics.getInstance(context);
        analytics.setLocalDispatchPeriod(1800);
        if (AppConfigHelper.isDevelopment()) {
            analytics.setDryRun(true);
        }
        analyticsTracker = analytics.newTracker(context.getResources().getString(R.string.google_analytics_id));
        analyticsTracker.enableExceptionReporting(true);
        analyticsTracker.enableAdvertisingIdCollection(false);
        analyticsTracker.enableAutoActivityTracking(true);
    }

    public static void trackAnalyticsEvent(String screenName, String actionName, String value) {
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

    public static void trackAnalyticsEvent(String screenName, String actionName) {
        trackAnalyticsEvent(screenName, actionName, "");
    }
}