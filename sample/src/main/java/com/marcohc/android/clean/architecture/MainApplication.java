package com.marcohc.android.clean.architecture;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.multidex.MultiDexApplication;
import android.util.Log;

import com.marcohc.android.clean.architecture.common.util.Constants;
import com.marcohc.android.clean.architecture.data.repository.UserRepository;
import com.marcohc.android.clean.architecture.presentation.BuildConfig;
import com.marcohc.android.clean.architecture.presentation.R;
import com.marcohc.android.clean.architecture.presentation.notification.NotificationManager;
import com.marcohc.android.clean.architecture.presentation.util.AnalyticsManager;
import com.marcohc.android.clean.architecture.presentation.util.PreferencesConstants;
import com.marcohc.helperoid.PreferencesHelper;
import com.squareup.leakcanary.LeakCanary;
import com.vincentbrison.openlibraries.android.dualcache.lib.DualCacheContextUtils;
import com.vincentbrison.openlibraries.android.dualcache.lib.DualCacheLogUtils;

import java.util.concurrent.Semaphore;

import cat.ereza.customactivityoncrash.CustomActivityOnCrash;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

@SuppressLint({"SimpleDateFormat", "DefaultLocale"})
public class MainApplication extends MultiDexApplication {

    // ************************************************************************************************************************************************************************
    // * Attributes
    // ************************************************************************************************************************************************************************

    public final static Semaphore SEMAPHORE_1 = new Semaphore(0);
    private static Boolean isAlreadyInitialized = false;

    // ************************************************************************************************************************************************************************
    // * Initialization methods
    // ************************************************************************************************************************************************************************

    @Override
    public void onCreate() {

        super.onCreate();

        if (isDevelopment()) {

            initializeStrictMode();

            initializeLeakCanary();

        }

        // Loading task. Other classes must wait until the app is initialized
        AsyncTask task = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] params) {

                // Load all data
                Log.d(Constants.LOG_TAG, "1 - MainApplication - Start loading data");

                initializeCrashlytics();
                initializeNotificationManager();
                initializePreferences();
                initializeCalligraphy();
                initializeAnalytics();
                initializeRepositories();
                initializeCache();
                initializeCustomCrash();

                // Notify load finished
                Log.d(Constants.LOG_TAG, "2 - MainApplication - Finish loading data");
                isAlreadyInitialized = true;
                MainApplication.SEMAPHORE_1.release();

                return null;
            }
        };
        task.execute();

    }

    private void initializeCustomCrash() {
        CustomActivityOnCrash.install(this);
    }

    private void initializeCache() {
        if (isDevelopment()) {
            DualCacheLogUtils.enableLog();
        }
        DualCacheContextUtils.setContext(getApplicationContext());
    }

    private void initializeLeakCanary() {
        LeakCanary.install(this);
    }

    private void initializeStrictMode() {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads()
                .detectDiskWrites()
                .detectAll()
                .penaltyLog()
                .build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects()
                .detectLeakedClosableObjects()
                .penaltyLog()
                .penaltyDeath()
                .build());
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
        PreferencesHelper.initialize(this, PreferencesConstants.SHARED_PREFERENCES_NAME);
    }

    private void initializeCalligraphy() {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                        .setDefaultFontPath("fonts/Roboto-Regular.ttf")
                        .setFontAttrId(R.attr.fontPath)
                        .build()
        );
    }

    // ************************************************************************************************************************************************************************
    // * Other methods
    // ************************************************************************************************************************************************************************

    public static boolean isDevelopment() {
        return BuildConfig.BUILD_TYPE.equals("debug");
    }

    public static boolean isAcceptance() {
        return BuildConfig.BUILD_TYPE.equals("acceptance");
    }

    /**
     * Used from other
     */
    public static void waitUntilMainApplicationIsInitialized() {
        try {
            if (!isAlreadyInitialized) {
                Log.d(Constants.LOG_TAG, "Waiting for MainApplication to finish loading data...");
                MainApplication.SEMAPHORE_1.acquire();
            } else {
                Log.d(Constants.LOG_TAG, "MainApplication already initialized");
            }
        } catch (InterruptedException ignored) {
        }
    }

//    private static long timer;
//    private static int stepNumber;
//
//    public static void startTimer() {
//        Log.d(NavigationHelper.LOG_TAG, String.format("Starting timer..."));
//        timer = System.currentTimeMillis();
//        stepNumber = 0;
//    }
//
//    public static void logTimer() {
//        Long difference = System.currentTimeMillis() - timer;
//        Log.d(NavigationHelper.LOG_TAG, String.format("%d - Time: %d", stepNumber++, difference));
//        timer = System.currentTimeMillis();
//    }
}
