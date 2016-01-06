package com.marcohc.android.clean.architecture.presentation.presenter.inter;


import android.app.Activity;
import android.content.Intent;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.marcohc.android.clean.architecture.presentation.view.inter.AuthenticationView;


public interface AuthenticationPresenter extends MvpPresenter<AuthenticationView> {

    void loginWithFacebook();

    void onLoginWithFacebookResult(int requestCode, int resultCode, Intent data);

    void onViewCreated(Activity activity);
}
