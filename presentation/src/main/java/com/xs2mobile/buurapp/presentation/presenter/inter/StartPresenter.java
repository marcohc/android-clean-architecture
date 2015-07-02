package com.xs2mobile.buurapp.presentation.presenter.inter;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.xs2mobile.buurapp.presentation.view.inter.StartView;

public interface StartPresenter extends MvpPresenter<StartView> {

    void onLogInButtonClick();

    void onSignUpButtonClick();

    boolean isUserLoggedIn();

}
