package com.marcohc.architecture.app.presentation.presenter.inter;

import com.marcohc.architecture.presentation.mosby.mvp.MvpPresenter;
import com.marcohc.architecture.app.presentation.view.inter.StartView;

public interface StartPresenter extends MvpPresenter<StartView> {

    boolean isUserLoggedIn();

    boolean isFirstTimeInTheApp();
}
