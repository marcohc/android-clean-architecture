package com.marcohc.android.clean.architecture.presentation.presenter.impl;

import com.marcohc.android.clean.architecture.domain.interactor.IsFirstTimeInTheAppUseCase;
import com.marcohc.android.clean.architecture.domain.interactor.IsUserLoggedInUseCase;
import com.marcohc.android.clean.architecture.presentation.BasePresenter;
import com.marcohc.android.clean.architecture.presentation.presenter.inter.StartPresenter;
import com.marcohc.android.clean.architecture.presentation.view.inter.StartView;

public class StartPresenterImpl extends BasePresenter<StartView> implements StartPresenter {

    @Override
    public boolean isUserLoggedIn() {
        return new IsUserLoggedInUseCase().execute();
    }

    @Override
    public boolean isFirstTimeInTheApp() {
        return new IsFirstTimeInTheAppUseCase().execute();
    }
}