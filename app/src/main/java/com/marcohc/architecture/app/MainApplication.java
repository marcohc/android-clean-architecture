package com.marcohc.architecture.app;

import android.annotation.SuppressLint;
import android.os.StrictMode;

import com.marcohc.architecture.app.data.repository.UserRepository;
import com.marcohc.architecture.app.presentation.util.BuildConfigHelper;
import com.marcohc.architecture.common.helper.AnalyticsHelper;
import com.marcohc.architecture.common.helper.AppInfoHelper;
import com.marcohc.architecture.common.helper.PreferencesHelper;
import com.marcohc.architecture.common.helper.TimerLog;
import com.squareup.leakcanary.LeakCanary;

import java.util.concurrent.Semaphore;

import cat.ereza.customactivityoncrash.CustomActivityOnCrash;
import timber.log.Timber;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

@SuppressLint({"SimpleDateFormat", "DefaultLocale"})
public class MainApplication extends android.app.Application {

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

        if (BuildConfigHelper.getInstance().isDevelopment()) {
            setUpStrictMode();
            setUpLeakCanary();
        }

        setUpTimber();

        new Thread(new Runnable() {
            @Override
            public void run() {
                TimerLog timer = TimerLog.getInstance("MainApplication.setUp");

                // Load all data
                Timber.d("1 - MainApplication - Start loading data");

                setUpCustomCrash();
                timer.logStep();
                setUpPreferences();
                timer.logStep();
                setUpCalligraphy();
                timer.logStep();
                setUpAnalytics();
                timer.logStep();
                setUpRepositories();
                timer.logStep();

                // Notify load finished
                Timber.d("2 - MainApplication - Finish loading data");
                isAlreadyInitialized = true;
                MainApplication.SEMAPHORE_1.release();

                timer.logTotal();
            }
        }).start();
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
        if (BuildConfigHelper.getInstance().isDevelopment()) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    private void setUpAnalytics() {
        AnalyticsHelper.setUp(this, !BuildConfigHelper.getInstance().isProduction());
    }

    private void setUpPreferences() {
        PreferencesHelper.setUp(this);
        AppInfoHelper.setUp(PreferencesHelper.getInstance());
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
                Timber.d("Waiting for MainApplication to finish loading data...");
                MainApplication.SEMAPHORE_1.acquire();
            } else {
                Timber.d("MainApplication already setUp");
            }
        } catch (InterruptedException ignored) {
        }
    }
}
