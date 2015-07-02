package com.xs2mobile.buurapp.presentation.util;

import android.content.Context;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.HitBuilders.EventBuilder;
import com.google.android.gms.analytics.Logger.LogLevel;
import com.google.android.gms.analytics.Tracker;
import com.xs2mobile.buurapp.MainApplication;
import com.xs2mobile.buurapp.R;

public class AnalyticsManager {

    private static Tracker analyticsTracker;

    public static void initialize(Context context) {
        GoogleAnalytics analytics = GoogleAnalytics.getInstance(context);
        analytics.getLogger().setLogLevel(LogLevel.VERBOSE);
        if (MainApplication.isDevelopment()) {
            analytics.setDryRun(true);
        }
        // Set the log level to verbose.
        analyticsTracker = analytics.newTracker(R.xml.global_tracker);
    }

    public static void trackAnalyticsEvent(String screenName, String actionName, String value) {
        EventBuilder builder = new HitBuilders.EventBuilder();
        builder.setCategory(screenName);
        builder.setAction(actionName);
        builder.setLabel(value);
        analyticsTracker.setScreenName(screenName);
        analyticsTracker.send(builder.build());
        analyticsTracker.setScreenName(null);
    }

    public static void trackAnalyticsEvent(String screenName, String actionName) {
        trackAnalyticsEvent(screenName, actionName, "");
    }
}
