package com.marcohc.android.clean.architecture;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.multidex.MultiDexApplication;

import com.marcohc.android.clean.architecture.common.util.Constants;
import com.marcohc.android.clean.architecture.data.repository.UserRepository;
import com.marcohc.android.clean.architecture.presentation.notification.NotificationManager;
import com.marcohc.android.clean.architecture.presentation.util.AnalyticsManager;
import com.marcohc.android.clean.architecture.presentation.util.PreferencesConstants;
import com.marcohc.helperoid.PreferencesHelper;
import com.squareup.leakcanary.LeakCanary;
import com.vincentbrison.openlibraries.android.dualcache.lib.DualCacheContextUtils;
import com.vincentbrison.openlibraries.android.dualcache.lib.DualCacheLogUtils;
import com.marcohc.android.clean.architecture.sample.BuildConfig;

import java.util.concurrent.Semaphore;

import cat.ereza.customactivityoncrash.CustomActivityOnCrash;
import timber.log.Timber;
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

                initializeTimber();

                // Load all data
                Timber.d(Constants.LOG_TAG, "1 - MainApplication - Start loading data");

                initializeCustomCrash();
                initializeFabric();
                initializeNotificationManager();
                initializePreferences();
                initializeCalligraphy();
                initializeAnalytics();
                initializeRepositories();
                initializeCache();

                // Notify load finished
                Timber.d(Constants.LOG_TAG, "2 - MainApplication - Finish loading data");
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

    private void initializeFabric() {
        // TODO: Uncomment this to make Fabric work
//        CrashlyticsCore core = new CrashlyticsCore.Builder()
//                .disabled(isDevelopment())
//                .build();
//        Fabric.with(this, new Crashlytics.Builder().core(core).build());
    }

    private void initializeTimber() {
        if (isDevelopment()) {
            Timber.plant(new Timber.DebugTree());
        } else {
            // TODO: Uncomment this to make Timber and Fabric work together
//            Timber.plant(new CrashlyticsTree());
        }
    }

    // TODO: Uncomment this to make Timber and Fabric work together
//    public class CrashlyticsTree extends Timber.Tree {
//
//        private static final String CRASHLYTICS_KEY_PRIORITY = "priority";
//        private static final String CRASHLYTICS_KEY_TAG = "tag";
//        private static final String CRASHLYTICS_KEY_MESSAGE = "message";
//
//        @Override
//        protected void log(int priority, @Nullable String tag, @Nullable String message, @Nullable Throwable t) {
//            if (priority == Log.VERBOSE || priority == Log.DEBUG || priority == Log.INFO) {
//                return;
//            }
//
//            Crashlytics.setInt(CRASHLYTICS_KEY_PRIORITY, priority);
//            Crashlytics.setString(CRASHLYTICS_KEY_TAG, tag);
//            Crashlytics.setString(CRASHLYTICS_KEY_MESSAGE, message);
//
//            if (t == null) {
//                Crashlytics.logException(new Exception(message));
//            } else {
//                Crashlytics.logException(t);
//            }
//        }
//    }

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

    public static boolean isProduction() {
        return BuildConfig.BUILD_TYPE.equals("release");
    }

    /**
     * Used from other
     */
    public static void waitUntilMainApplicationIsInitialized() {
        try {
            if (!isAlreadyInitialized) {
                Timber.d(Constants.LOG_TAG, "Waiting for MainApplication to finish loading data...");
                MainApplication.SEMAPHORE_1.acquire();
            } else {
                Timber.d(Constants.LOG_TAG, "MainApplication already initialized");
            }
        } catch (InterruptedException ignored) {
        }
    }
}
