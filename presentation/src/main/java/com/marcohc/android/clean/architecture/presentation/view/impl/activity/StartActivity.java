package com.marcohc.android.clean.architecture.presentation.view.impl.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.marcohc.android.clean.architecture.presentation.R;
import com.marcohc.android.clean.architecture.presentation.presenter.impl.StartPresenterImpl;
import com.marcohc.android.clean.architecture.presentation.presenter.inter.StartPresenter;
import com.marcohc.android.clean.architecture.presentation.view.inter.StartView;

import butterknife.OnClick;

public class StartActivity extends BaseMvpActivity<StartView, StartPresenter> implements StartView {

    // ************************************************************************************************************************************************************************
    // * Attributes
    // ************************************************************************************************************************************************************************

    private static int SPLASH_TIME_OUT = 500;

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

        if (presenter.isUserLoggedIn()) {
            goToMain();
        } else {
            // Login
            setContentView(R.layout.start_activity);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
//                    Intent intent = new Intent(StartActivity.this, MainMvpActivity.class);
//                    startActivity(intent);
//                    finish();
                }
            }, SPLASH_TIME_OUT);
        }
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

    @OnClick(R.id.logInButton)
    void onLogInButtonClick() {
        presenter.onLogInButtonClick();
    }

    @OnClick(R.id.signUpButton)
    void onSignUpButtonClick() {
        presenter.onSignUpButtonClick();
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
