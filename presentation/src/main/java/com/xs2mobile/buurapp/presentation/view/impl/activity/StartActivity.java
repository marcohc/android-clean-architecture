package com.xs2mobile.buurapp.presentation.view.impl.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.xs2mobile.buurapp.R;
import com.xs2mobile.buurapp.presentation.presenter.impl.StartPresenterImpl;
import com.xs2mobile.buurapp.presentation.presenter.inter.StartPresenter;
import com.xs2mobile.buurapp.presentation.view.inter.StartView;

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

    @Override
    public void goToLogIn() {
        Intent intent = new Intent(this, LogInActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public void goToSignUp() {
        Intent intent = new Intent(this, ProfileDetailActivity.class);
        startActivityForResult(intent, 1);
    }

    private void goToMain() {
        Intent intent = new Intent(StartActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
