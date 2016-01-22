package com.marcohc.android.clean.architecture.presentation.view.impl.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.marcohc.android.clean.architecture.MainApplication;
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

            public boolean isUserLoggedIn;
            public boolean isFirstTimeInTheApp;

            @Override
            protected Object doInBackground(Object[] params) {

                Timber.d("2 - StartActivity: Waiting for MainApplication to finish loading data");
                MainApplication.waitUntilMainApplicationIsInitialized();

                isFirstTimeInTheApp = presenter.isFirstTimeInTheApp();
                isUserLoggedIn = presenter.isUserLoggedIn();

                return null;
            }

            @Override
            protected void onPostExecute(Object o) {

                Timber.d("3 - StartActivity: Going to main");

                if (isFirstTimeInTheApp) {
                    // First app opening and then tap on notification
                    if (isUserLoggedIn) {
                        goToMain();
                    }
                    // First app opening
                    else {
                        goToTutorial();
                    }
                } else {
                    // User logged in
                    if (isUserLoggedIn) {
                        goToMain();
                    }
                    // User logged out
                    else {
                        goToAuthentication();
                    }
                }
            }
        };
        task.execute();

        Timber.d("1 - StartActivity: Showing splash screen");
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
