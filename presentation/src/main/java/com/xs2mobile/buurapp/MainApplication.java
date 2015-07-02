package com.xs2mobile.buurapp;

import android.annotation.SuppressLint;
import android.support.multidex.MultiDexApplication;

import com.crashlytics.android.Crashlytics;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.picasso.Picasso;
import com.xs2mobile.buurapp.presentation.notification.NotificationManager;
import com.xs2mobile.buurapp.presentation.util.AnalyticsManager;
import com.xs2mobile.buurapp.presentation.util.FileSystemManager;
import com.xs2mobile.buurapp.presentation.util.PreferencesManager;

import io.fabric.sdk.android.Fabric;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

@SuppressLint({"SimpleDateFormat", "DefaultLocale"})
public class MainApplication extends MultiDexApplication {

    // ************************************************************************************************************************************************************************
    // * Attributes
    // ************************************************************************************************************************************************************************

    private static boolean IS_DEVELOPMENT = true;

    public static boolean isDevelopment() {
        return IS_DEVELOPMENT;
    }

    // ************************************************************************************************************************************************************************
    // * Initialization methods
    // ************************************************************************************************************************************************************************

    @Override
    public void onCreate() {

        super.onCreate();

        LeakCanary.install(this);

        initializeNotificationManager();

        if (!isDevelopment()) {
            initializeCrashlytics();
        }

        initializePreferences();

        initializeCalligraphy();

        initializeFileSystemManager();

        initializePicasso();

        initializeAnalytics();

        if (IS_DEVELOPMENT) {
//            ApplicationHelper.vibrate(this);
        }
    }

    private void initializeNotificationManager() {
        NotificationManager.initialize(getApplicationContext());
    }

    private void initializeCrashlytics() {
        Fabric.with(this, new Crashlytics());
    }

    private void initializeAnalytics() {
        AnalyticsManager.initialize(this);
    }

    private void initializePreferences() {
        PreferencesManager.initialize(getApplicationContext());
    }

    private void initializePicasso() {
        Picasso picasso = Picasso.with(getApplicationContext());
        picasso.setIndicatorsEnabled(IS_DEVELOPMENT);
    }

    private void initializeFileSystemManager() {
        FileSystemManager.initialize();
    }

    private void initializeCalligraphy() {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                        .setDefaultFontPath("fonts/Roboto-Regular.ttf")
                        .setFontAttrId(R.attr.fontPath)
                        .build()
        );
    }
}
