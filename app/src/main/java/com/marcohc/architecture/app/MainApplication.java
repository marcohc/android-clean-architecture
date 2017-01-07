package com.marcohc.architecture.app;

import android.annotation.SuppressLint;
import android.os.StrictMode;

import com.marcohc.architecture.app.internal.di.ApplicationInjector;
import com.marcohc.architecture.app.presentation.util.BuildConfigUtils;
import com.marcohc.architecture.common.util.TimerUtils;
import com.squareup.leakcanary.LeakCanary;

import cat.ereza.customactivityoncrash.CustomActivityOnCrash;
import timber.log.Timber;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

@SuppressLint({ "SimpleDateFormat", "DefaultLocale" })
public class MainApplication extends android.app.Application {

    @Override
    public void onCreate() {

        super.onCreate();

        if (BuildConfigUtils.getInstance().isDevelopment()) {
            setUpStrictMode();
            setUpLeakCanary();
        }

        setUpTimber();

        TimerUtils timer = TimerUtils.getInstance("MainApplication.setUp");

        // Load all data
        Timber.d("1 - MainApplication - Start loading data");

        setUpCustomCrash();
        timer.logStep();
        setUpCalligraphy();
        timer.logStep();
        setUpDagger();
        timer.logStep();

        // Notify load finished
        Timber.d("2 - MainApplication - Finish loading data");

        timer.logTotal();
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

    private void setUpTimber() {
        if (BuildConfigUtils.getInstance().isDevelopment()) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    private void setUpDagger() {
        ApplicationInjector.inject(MainApplication.this);
    }

    private void setUpCalligraphy() {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                                              .setDefaultFontPath("fonts/Roboto-Regular.ttf")
                                              .setFontAttrId(R.attr.fontPath)
                                              .build()
        );
    }
}
