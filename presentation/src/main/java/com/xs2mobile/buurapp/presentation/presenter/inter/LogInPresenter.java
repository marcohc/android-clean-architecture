package com.xs2mobile.buurapp.presentation.presenter.inter;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.xs2mobile.buurapp.presentation.view.inter.LogInView;

public interface LogInPresenter extends MvpPresenter<LogInView> {

    void onForgotPasswordTextClick();

    void onActionDoneClick();

    void goToMain();
}
