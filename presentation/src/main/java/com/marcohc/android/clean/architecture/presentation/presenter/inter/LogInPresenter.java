package com.marcohc.android.clean.architecture.presentation.presenter.inter;


import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.marcohc.android.clean.architecture.presentation.view.inter.LogInView;

public interface LogInPresenter extends MvpPresenter<LogInView> {

    void onViewCreated();

    void onActionDoneClick();

}
