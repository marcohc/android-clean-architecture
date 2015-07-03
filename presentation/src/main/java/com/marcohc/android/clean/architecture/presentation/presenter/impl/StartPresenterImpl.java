package com.marcohc.android.clean.architecture.presentation.presenter.impl;

import com.marcohc.android.clean.architecture.presentation.presenter.BasePresenter;
import com.marcohc.android.clean.architecture.presentation.presenter.inter.StartPresenter;
import com.marcohc.android.clean.architecture.presentation.view.inter.StartView;

public class StartPresenterImpl extends BasePresenter<StartView> implements StartPresenter {

    @Override
    public void onLogInButtonClick() {
    }

    @Override
    public void onSignUpButtonClick() {
    }

    @Override
    public boolean isUserLoggedIn() {
//        return UserRepository.getInstance().isUserLoggedIn();
        return true;
    }
}
