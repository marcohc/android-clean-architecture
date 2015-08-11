package com.marcohc.android.clean.architecture;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.multidex.MultiDexApplication;
import android.util.Log;

import com.facebook.stetho.Stetho;
import com.marcohc.android.clean.architecture.common.bus.BusProvider;
import com.marcohc.android.clean.architecture.common.util.Constants;
import com.marcohc.android.clean.architecture.data.repository.UserRepository;
import com.marcohc.android.clean.architecture.data.util.PreferencesManager;
import com.marcohc.android.clean.architecture.presentation.BuildConfig;
import com.marcohc.android.clean.architecture.presentation.R;
import com.marcohc.android.clean.architecture.presentation.notification.NotificationManager;
import com.marcohc.android.clean.architecture.presentation.util.AnalyticsManager;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.picasso.Picasso;

import java.util.concurrent.Semaphore;

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
                initializePicasso();
                initializeAnalytics();
                initializeBus();
                initializeRepositories();
                initializeStetho();

                // Notify load finished
                Log.d(Constants.LOG_TAG, "2 - MainApplication - Finish loading data");
                MainApplication.SEMAPHORE_1.release();

                synchronized (isAlreadyInitialized) {
                    isAlreadyInitialized = true;
                }

                return null;
            }
        };
        task.execute();

    }

    private void initializeStetho() {
        Stetho.initialize(Stetho.newInitializerBuilder(this)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                .build());
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
        picasso.setIndicatorsEnabled(isDevelopment());
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
        return BuildConfig.DEBUG;
    }

    /**
     * Used from other
     */
    public static void waitUntilMainApplicationIsInitialized() {
        try {
            synchronized (isAlreadyInitialized) {
                if (!isAlreadyInitialized) {
                    Log.d(Constants.LOG_TAG, "Waiting for MainApplication to finish loading data...");
                    MainApplication.SEMAPHORE_1.acquire();
                } else {
                    Log.d(Constants.LOG_TAG, "MainApplication already initialized");
                }
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
