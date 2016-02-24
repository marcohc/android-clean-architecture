package com.marcohc.architecture.presentation.presenter.inter;

import com.marcohc.architecture.presentation.mosby.mvp.MvpPresenter;
import com.marcohc.architecture.presentation.view.inter.SignUpView;

public interface SignUpPresenter extends MvpPresenter<SignUpView> {

    void onActionDoneClick();

}
