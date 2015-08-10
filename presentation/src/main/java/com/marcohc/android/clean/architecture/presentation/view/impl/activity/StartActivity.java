package com.marcohc.android.clean.architecture.presentation.view.impl.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

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

        // Login
        setContentView(R.layout.start_activity);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Skip login
                if (presenter.isUserLoggedIn()) {
                    goToMain();
                } else {
                    goToLogin();
                }
            }
        }, SPLASH_TIME_OUT);
    }

    // ************************************************************************************************************************************************************************
    // * Event handler methods
    // ************************************************************************************************************************************************************************

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            goToMain();
            finish();
        }
    }

    // ************************************************************************************************************************************************************************
    // * View interface methods
    // ************************************************************************************************************************************************************************

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
