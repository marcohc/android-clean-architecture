package com.marcohc.android.clean.architecture;

import android.annotation.SuppressLint;
import android.support.multidex.MultiDexApplication;

import com.marcohc.android.clean.architecture.common.bus.BusProvider;
import com.marcohc.android.clean.architecture.data.repository.UserRepository;
import com.marcohc.android.clean.architecture.presentation.R;
import com.marcohc.android.clean.architecture.presentation.notification.NotificationManager;
import com.marcohc.android.clean.architecture.presentation.util.AnalyticsManager;
import com.marcohc.android.clean.architecture.presentation.util.PreferencesManager;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.picasso.Picasso;

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

        if (!isDevelopment()) {
            initializeCrashlytics();
        }

        LeakCanary.install(this);

        initializeNotificationManager();

        initializePreferences();

        initializeCalligraphy();

        initializePicasso();

        initializeAnalytics();

        initializeBus();

        initializeRepositories();

    }

    private void initializeBus() {
        BusProvider.getInstance();
    }

    private void initializeRepositories() {
        UserRepository.initialize();
    }

    private void initializeNotificationManager() {
        NotificationManager.initialize(getApplicationContext());
    }

    private void initializeCrashlytics() {
//        Fabric.with(this, new Crashlytics());
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

    private void initializeCalligraphy() {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                        .setDefaultFontPath("fonts/Roboto-Regular.ttf")
                        .setFontAttrId(R.attr.fontPath)
                        .build()
        );
    }
}
