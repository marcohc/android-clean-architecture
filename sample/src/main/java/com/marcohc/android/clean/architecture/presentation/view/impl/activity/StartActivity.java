package com.marcohc.android.clean.architecture.presentation.view.impl.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.marcohc.android.clean.architecture.MainApplication;
import com.marcohc.android.clean.architecture.common.util.Constants;
import com.marcohc.android.clean.architecture.presentation.presenter.impl.StartPresenterImpl;
import com.marcohc.android.clean.architecture.presentation.presenter.inter.StartPresenter;
import com.marcohc.android.clean.architecture.presentation.util.NavigationManager;
import com.marcohc.android.clean.architecture.presentation.view.activity.BaseMvpActivity;
import com.marcohc.android.clean.architecture.presentation.view.inter.StartView;
import com.marcohc.android.clean.architecture.sample.R;

import timber.log.Timber;

public class StartActivity extends BaseMvpActivity<StartView, StartPresenter> implements StartView {

    // ************************************************************************************************************************************************************************
    // * Attributes
    // ************************************************************************************************************************************************************************

    private static final int SPLASH_TIME_OUT = 1000;

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

                try {
                    Thread.sleep(SPLASH_TIME_OUT);
                } catch (InterruptedException ignored) {
                }

                Timber.d(Constants.LOG_TAG, "2 - StartActivity: Waiting for MainApplication to finish loading data");
                MainApplication.waitUntilMainApplicationIsInitialized();

                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                Timber.d(Constants.LOG_TAG, "3 - StartActivity: Going to main");

                if (presenter.isFirstTimeInTheApp()) {
                    goToTutorial();
                } else if (presenter.isUserLoggedIn()) {
                    goToMain();
                } else {
                    goToAuthentication();
                }
            }
        };
        task.execute();

        Timber.d(Constants.LOG_TAG, "1 - StartActivity: Showing splash screen");
    }

    // ************************************************************************************************************************************************************************
    // * Event handler methods
    // ************************************************************************************************************************************************************************

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == NavigationManager.TUTORIAL_REQUEST_CODE) {
            goToAuthentication();
        } else {
            goToMain();
        }
    }

    // ************************************************************************************************************************************************************************
    // * View interface methods
    // ************************************************************************************************************************************************************************

    private void goToTutorial() {
        Intent intent = new Intent(StartActivity.this, TutorialActivity.class);
        startActivityForResult(intent, NavigationManager.TUTORIAL_REQUEST_CODE);
    }

    private void goToMain() {
        Intent intent = new Intent(StartActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void goToAuthentication() {
        Intent intent = new Intent(StartActivity.this, AuthenticationActivity.class);
        startActivity(intent);
        finish();
    }
}
