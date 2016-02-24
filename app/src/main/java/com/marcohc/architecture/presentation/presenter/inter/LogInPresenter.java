package com.marcohc.architecture.presentation.presenter.inter;


import com.marcohc.architecture.presentation.mosby.mvp.MvpPresenter;
import com.marcohc.architecture.presentation.view.inter.LogInView;

public interface LogInPresenter extends MvpPresenter<LogInView> {

    void onActionDoneClick();

}
