package com.marcohc.architecture.presentation.presenter.inter;

import com.marcohc.architecture.presentation.mosby.mvp.MvpPresenter;
import com.marcohc.architecture.presentation.view.inter.StartView;

public interface StartPresenter extends MvpPresenter<StartView> {

    boolean isUserLoggedIn();

    boolean isFirstTimeInTheApp();
}
