package com.marcohc.architecture.app.presentation.activity.impl;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.marcohc.architecture.app.MainApplication;
import com.marcohc.architecture.app.R;
import com.marcohc.architecture.app.presentation.activity.inter.StartView;
import com.marcohc.architecture.app.presentation.presenter.impl.StartPresenterImpl;
import com.marcohc.architecture.app.presentation.presenter.inter.StartPresenter;
import com.marcohc.architecture.presentation.view.BaseView;
import com.marcohc.architecture.presentation.view.activity.BaseMvpActivity;

import timber.log.Timber;

public class StartActivity extends BaseMvpActivity<StartView, StartPresenter> implements BaseView {


    // ************************************************************************************************************************************************************************
    // * Attributes
    // ************************************************************************************************************************************************************************

    private long SLEEP_TIME = 2000;

    // ************************************************************************************************************************************************************************
    // * Initialization methods
    // ************************************************************************************************************************************************************************

    @NonNull
    @Override
    public StartPresenter createPresenter() {
        return new StartPresenterImpl();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.start_activity);

        // Show splash screen and wait until MainApplication has finished loading stuff
        AsyncTask task = new AsyncTask() {

            @Override
            protected Object doInBackground(Object[] params) {

                Timber.d("2 - StartActivity: Waiting for MainApplication to finish loading data");

                try {
                    Thread.sleep(SLEEP_TIME);
                } catch (InterruptedException ignored) {
                }

                MainApplication.waitUntilMainApplicationIsInitialized();

                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                Timber.d("3 - StartActivity: Going to main");
                goToMain();
            }
        };
        task.execute();

        Timber.d("1 - StartActivity: Showing splash screen");
    }

    // ************************************************************************************************************************************************************************
    // * View interface methods
    // ************************************************************************************************************************************************************************

    private void goToMain() {
        Intent intent = new Intent(StartActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
