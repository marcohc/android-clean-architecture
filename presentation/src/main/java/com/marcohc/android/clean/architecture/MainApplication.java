package com.marcohc.android.clean.architecture;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.StrictMode;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.marcohc.android.clean.architecture.common.bus.BusProvider;
import com.marcohc.android.clean.architecture.data.repository.UserRepository;
import com.marcohc.android.clean.architecture.data.util.PreferencesManager;
import com.marcohc.android.clean.architecture.presentation.R;
import com.marcohc.android.clean.architecture.presentation.notification.NotificationManager;
import com.marcohc.android.clean.architecture.presentation.util.AnalyticsManager;
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
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {

        super.onCreate();

        if (isDevelopment()) {

            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                    .detectDiskReads()
                    .detectDiskWrites()
                    .detectNetwork()   // or .detectAll() for all detectable problems
                    .penaltyLog()
                    .build());
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                    .detectLeakedSqlLiteObjects()
                    .detectLeakedClosableObjects()
                    .penaltyLog()
                    .penaltyDeath()
                    .build());

            LeakCanary.install(this);
        } else {
            initializeCrashlytics();
        }

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
        // TODO: Get all repositories by reflection and initialize them in order to connect to the bus
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
