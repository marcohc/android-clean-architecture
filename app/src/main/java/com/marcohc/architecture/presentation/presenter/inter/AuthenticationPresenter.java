package com.marcohc.architecture.presentation.presenter.inter;


import android.app.Activity;
import android.content.Intent;

import com.marcohc.architecture.presentation.mosby.mvp.MvpPresenter;
import com.marcohc.architecture.presentation.view.inter.AuthenticationView;


public interface AuthenticationPresenter extends MvpPresenter<AuthenticationView> {

    void loginWithFacebook();

    void onLoginWithFacebookResult(int requestCode, int resultCode, Intent data);

    void onViewCreated(Activity activity);
}
