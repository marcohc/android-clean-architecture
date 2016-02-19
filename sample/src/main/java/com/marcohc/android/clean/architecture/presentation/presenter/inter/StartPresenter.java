package com.marcohc.android.clean.architecture.presentation.presenter.inter;

import com.marcohc.android.clean.architecture.presentation.mosby.mvp.MvpPresenter;
import com.marcohc.android.clean.architecture.presentation.view.inter.StartView;

public interface StartPresenter extends MvpPresenter<StartView> {

    boolean isUserLoggedIn();

    boolean isFirstTimeInTheApp();
}
