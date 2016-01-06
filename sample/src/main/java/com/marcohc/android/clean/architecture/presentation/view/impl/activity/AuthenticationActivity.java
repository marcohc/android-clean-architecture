package com.marcohc.android.clean.architecture.presentation.view.impl.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.marcohc.android.clean.architecture.sample.R;
import com.marcohc.android.clean.architecture.presentation.presenter.impl.AuthenticationPresenterImpl;
import com.marcohc.android.clean.architecture.presentation.presenter.inter.AuthenticationPresenter;
import com.marcohc.android.clean.architecture.presentation.view.activity.BaseMvpActivity;
import com.marcohc.android.clean.architecture.presentation.view.inter.AuthenticationView;

import butterknife.OnClick;

public class AuthenticationActivity extends BaseMvpActivity<AuthenticationView, AuthenticationPresenter> implements AuthenticationView {

    // ************************************************************************************************************************************************************************
    // * Attributes
    // ************************************************************************************************************************************************************************

    // View

    // ************************************************************************************************************************************************************************
    // * Initialization methods
    // ************************************************************************************************************************************************************************

    @NonNull
    @Override
    public AuthenticationPresenter createPresenter() {
        return new AuthenticationPresenterImpl();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.authentication_activity);
        presenter.onViewCreated(this);
    }

    // ************************************************************************************************************************************************************************
    // * BusEvent handler methods
    // ************************************************************************************************************************************************************************

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        presenter.onLoginWithFacebookResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @OnClick(R.id.logInWithFacebookContainer)
    protected void onLogInWithFacebookContainerClick() {
        presenter.loginWithFacebook();

    }

    @OnClick(R.id.logInWithEmailButton)
    protected void onLogInWithEmailButtonClick() {
        Intent intent = new Intent(this, LogInActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.signUpWithEmailButton)
    protected void onSignUpWithEmailButtonClick() {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.termsAndConditionsText)
    protected void onTermsAndConditionsTextClick() {
        Intent intent = new Intent(this, TermsAndConditionsActivity.class);
        startActivity(intent);
    }

    // ************************************************************************************************************************************************************************
    // * View interface methods
    // ************************************************************************************************************************************************************************

    @Override
    public void goToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
