package com.xs2mobile.buurapp.presentation.presenter.inter;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.xs2mobile.buurapp.presentation.view.inter.ForgottenPasswordView;

public interface ForgottenPasswordPresenter extends MvpPresenter<ForgottenPasswordView> {

    void onActionDoneClick();

}
