package com.marcohc.android.clean.architecture.presentation.presenter.impl;

import android.app.Activity;
import android.content.Intent;

import com.marcohc.android.clean.architecture.common.util.Constants;
import com.marcohc.android.clean.architecture.domain.bus.response.domain.LogInDomainResponse;
import com.marcohc.android.clean.architecture.presentation.BasePresenter;
import com.marcohc.android.clean.architecture.presentation.presenter.inter.AuthenticationPresenter;
import com.marcohc.android.clean.architecture.presentation.view.inter.AuthenticationView;
import com.marcohc.android.clean.architecture.sample.R;
import com.sromku.simple.fb.Permission;
import com.sromku.simple.fb.SimpleFacebook;
import com.sromku.simple.fb.listeners.OnLoginListener;

import java.util.List;

import timber.log.Timber;

@SuppressWarnings("ConstantConditions")
public class AuthenticationPresenterImpl extends BasePresenter<AuthenticationView> implements AuthenticationPresenter {

    private SimpleFacebook mSimpleFacebook;

    // ************************************************************************************************************************************************************************
    // * View handler methods
    // ************************************************************************************************************************************************************************

    @Override
    public void onViewCreated(Activity activity) {
        mSimpleFacebook = SimpleFacebook.getInstance(activity);
    }

    @Override
    public void loginWithFacebook() {
        mSimpleFacebook.login(new OnLoginListener() {
            @Override
            public void onLogin(final String accessToken, List<Permission> acceptedPermissions, List<Permission> declinedPermissions) {
                if (isViewAttached()) {
                    showDialog(getView().getResourceString(R.string.log_in_with_facebook), String.format("%s", getView().getResourceString(R.string.loading)), false);
                }
//                new LogInWithFacebookUseCase(accessToken).execute();
            }

            @Override
            public void onCancel() {
            }

            @Override
            public void onException(Throwable arg0) {
                Timber.w(Constants.LOG_TAG, String.format("onException: %s", arg0.getMessage()));
            }

            @Override
            public void onFail(String errorText) {
                Timber.w(Constants.LOG_TAG, String.format("onFail: %s", errorText));
            }
        });
    }

    @Override
    public void onLoginWithFacebookResult(int requestCode, int resultCode, Intent data) {
        mSimpleFacebook.onActivityResult(requestCode, resultCode, data);
    }

    // ************************************************************************************************************************************************************************
    // * Interactor handler methods
    // ************************************************************************************************************************************************************************

    public void onEventMainThread(LogInDomainResponse event) {
        hideDialog();
        if (isViewAttached()) {
            getView().goToMain();
        }
    }
}
