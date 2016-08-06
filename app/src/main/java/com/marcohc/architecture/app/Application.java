package com.marcohc.architecture.app;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.multidex.MultiDexApplication;

import com.marcohc.architecture.app.data.repository.UserRepository;
import com.marcohc.architecture.app.presentation.util.AppConfigHelper;
import com.marcohc.architecture.common.helper.AnalyticsHelper;
import com.marcohc.architecture.common.helper.AppInfoHelper;
import com.marcohc.architecture.common.helper.SecurePreferencesHelper;
import com.marcohc.architecture.common.helper.TimerLog;
import com.squareup.leakcanary.LeakCanary;

import java.util.concurrent.Semaphore;

import cat.ereza.customactivityoncrash.CustomActivityOnCrash;
import timber.log.Timber;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

@SuppressLint({"SimpleDateFormat", "DefaultLocale"})
public class Application extends MultiDexApplication {

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

        if (AppConfigHelper.getInstance().isDevelopment()) {
            setUpStrictMode();
            setUpLeakCanary();
        }

        setUpTimber();

        // Loading task. Other classes must wait until the app is initialized
        AsyncTask task = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] params) {

                TimerLog timer = new TimerLog("Application.setUp");
                timer.start();

                // Load all data
                Timber.d("1 - Application - Start loading data");

                setUpCustomCrash();
                setUpPreferences();
                setUpCalligraphy();
                setUpAnalytics();
                setUpRepositories();

                // Notify load finished
                Timber.d("2 - Application - Finish loading data");
                isAlreadyInitialized = true;
                Application.SEMAPHORE_1.release();

                timer.log();

                return null;
            }
        };
        task.execute();
    }

    private void setUpCustomCrash() {
        CustomActivityOnCrash.install(this);
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
        // TODO: Use Dagger to inject repositories
        UserRepository.setUp();
    }

    private void setUpTimber() {
        if (AppConfigHelper.getInstance().isDevelopment()) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    private void setUpAnalytics() {
        AnalyticsHelper.setUp(this, !AppConfigHelper.getInstance().isProduction());
    }

    private void setUpPreferences() {
        SecurePreferencesHelper.setUp(this);
        AppInfoHelper.setUp(SecurePreferencesHelper.getInstance());
    }

    private void setUpCalligraphy() {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Roboto-Regular.ttf")
                .setFontAttrId(com.marcohc.architecture.R.attr.fontPath)
                .build()
        );
    }

    // ************************************************************************************************************************************************************************
    // * Other methods
    // ************************************************************************************************************************************************************************

    /**
     * Used from other
     */
    public static void waitUntilApplicationIsInitialized() {
        try {
            if (!isAlreadyInitialized) {
                Timber.d("Waiting for Application to finish loading data...");
                Application.SEMAPHORE_1.acquire();
            } else {
                Timber.d("Application already setUp");
            }
        } catch (InterruptedException ignored) {
        }
    }
}