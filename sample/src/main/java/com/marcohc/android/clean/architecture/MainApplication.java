package com.marcohc.android.clean.architecture;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.multidex.MultiDexApplication;

import com.marcohc.android.clean.architecture.common.util.TimerLog;
import com.marcohc.android.clean.architecture.data.repository.UserRepository;
import com.marcohc.android.clean.architecture.presentation.notification.NotificareAppReceiver;
import com.marcohc.android.clean.architecture.presentation.notification.NotificationManager;
import com.marcohc.android.clean.architecture.presentation.util.AnalyticsManager;
import com.marcohc.android.clean.architecture.presentation.util.AppConfigHelper;
import com.marcohc.android.clean.architecture.presentation.util.PreferencesConstants;
import com.marcohc.helperoid.PreferencesHelper;
import com.squareup.leakcanary.LeakCanary;
import com.vincentbrison.openlibraries.android.dualcache.lib.DualCacheContextUtils;
import com.vincentbrison.openlibraries.android.dualcache.lib.DualCacheLogUtils;

import java.util.concurrent.Semaphore;

import cat.ereza.customactivityoncrash.CustomActivityOnCrash;
import re.notifica.Notificare;
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

        if (AppConfigHelper.isDevelopment()) {
            setUpStrictMode();
            setUpLeakCanary();
        }

        setUpTimber();

        // Loading task. Other classes must wait until the app is initialized
        AsyncTask task = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] params) {

                TimerLog timer = new TimerLog("MainApplication.setUp");
                timer.start();

                // Load all data
                Timber.d("1 - MainApplication - Start loading data");

                setUpCustomCrash();
                setUpFabric();
                setUpCustomNotifications();
                setUpPreferences();
                setUpCalligraphy();
                setUpAnalytics();
                setUpRepositories();
                setUpCache();

                // Notify load finished
                Timber.d("2 - MainApplication - Finish loading data");
                isAlreadyInitialized = true;
                MainApplication.SEMAPHORE_1.release();

                timer.log();

                return null;
            }
        };
        task.execute();

        setUpNotificare();
    }

    private void setUpCustomCrash() {
        CustomActivityOnCrash.install(this);
    }

    private void setUpCache() {
        if (AppConfigHelper.isDevelopment()) {
            DualCacheLogUtils.enableLog();
        }
        DualCacheContextUtils.setContext(getApplicationContext());
    }

    private void setUpLeakCanary() {
        LeakCanary.install(this);
    }

    private void setUpStrictMode() {
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

    private void setUpRepositories() {
        // TODO: Get all repositories by reflection and setUp them in order to connect to the bus
        UserRepository.setUp();
    }

    private void setUpNotificare() {
        TimerLog timer = new TimerLog("Notificare");
        timer.start();
        // Notificare library
        Notificare.shared().setDebugLogging(AppConfigHelper.isDevelopment());
        Notificare.shared().launch(MainApplication.this);
        Notificare.shared().setIntentReceiver(NotificareAppReceiver.class);
        timer.log();
    }

    private void setUpCustomNotifications() {
        // Used for custom notifications
        NotificationManager.setUp(getApplicationContext());
    }

    private void setUpFabric() {
        // TODO: Uncomment this to make Fabric work
//        CrashlyticsCore core = new CrashlyticsCore.Builder()
//                .disabled(isDevelopment())
//                .build();
//        Fabric.with(this, new Crashlytics.Builder().core(core).build());
    }

    private void setUpTimber() {
        if (AppConfigHelper.isDevelopment()) {
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

    private void setUpAnalytics() {
        AnalyticsManager.setUp(this);
    }

    private void setUpPreferences() {
        PreferencesHelper.initialize(this, PreferencesConstants.SHARED_PREFERENCES_NAME);
    }

    private void setUpCalligraphy() {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Roboto-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }

    // ************************************************************************************************************************************************************************
    // * Other methods
    // ************************************************************************************************************************************************************************

    /**
     * Used from other
     */
    public static void waitUntilMainApplicationIsInitialized() {
        try {
            if (!isAlreadyInitialized) {
                Timber.d("Waiting for MainApplication to finish loading data...");
                MainApplication.SEMAPHORE_1.acquire();
            } else {
                Timber.d("MainApplication already setUp");
            }
        } catch (InterruptedException ignored) {
        }
    }
}
