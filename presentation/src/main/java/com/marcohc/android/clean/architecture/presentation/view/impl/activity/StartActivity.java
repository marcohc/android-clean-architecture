package com.marcohc.android.clean.architecture.presentation.view.impl.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.marcohc.android.clean.architecture.MainApplication;
import com.marcohc.android.clean.architecture.common.util.Constants;
import com.marcohc.android.clean.architecture.presentation.R;
import com.marcohc.android.clean.architecture.presentation.presenter.impl.StartPresenterImpl;
import com.marcohc.android.clean.architecture.presentation.presenter.inter.StartPresenter;
import com.marcohc.android.clean.architecture.presentation.view.inter.StartView;

public class StartActivity extends BaseMvpActivity<StartView, StartPresenter> implements StartView {

    // ************************************************************************************************************************************************************************
    // * Attributes
    // ************************************************************************************************************************************************************************

    private static final int SPLASH_TIME_OUT = 2000;

    // ************************************************************************************************************************************************************************
    // * Initialization methods
    // ************************************************************************************************************************************************************************

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

                Log.d(Constants.LOG_TAG, "2 - StartActivity: Waiting for MainApplication to finish loading data");
                MainApplication.waitUntilMainApplicationIsInitialized();

                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                Log.d(Constants.LOG_TAG, "3 - StartActivity: Going to main");

                if (presenter.isFirstTimeInTheApp()) {
                    goToTutorial();
                } else if (presenter.isUserLoggedIn()) {
                    goToMain();
                } else {
                    goToLogin();
                }
            }
        };
        task.execute();

        Log.d(Constants.LOG_TAG, "1 - StartActivity: Showing splash screen");
    }

    // ************************************************************************************************************************************************************************
    // * Event handler methods
    // ************************************************************************************************************************************************************************

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == TutorialActivity.REQUEST_CODE) {
            goToLogin();
        } else {
            goToMain();
        }
    }

    // ************************************************************************************************************************************************************************
    // * View interface methods
    // ************************************************************************************************************************************************************************

    private void goToTutorial() {
        Intent intent = new Intent(StartActivity.this, TutorialActivity.class);
        startActivityForResult(intent, TutorialActivity.REQUEST_CODE);
    }

    private void goToMain() {
        Intent intent = new Intent(StartActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void goToLogin() {
        Intent intent = new Intent(StartActivity.this, LogInActivity.class);
        startActivity(intent);
        finish();
    }
}
