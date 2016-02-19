package com.marcohc.android.clean.architecture.presentation.presenter.inter;

import com.marcohc.android.clean.architecture.presentation.mosby.mvp.MvpPresenter;
import com.marcohc.android.clean.architecture.presentation.view.inter.SignUpView;

public interface SignUpPresenter extends MvpPresenter<SignUpView> {

    void onActionDoneClick();

}
