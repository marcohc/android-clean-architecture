package com.marcohc.architecture.app.presentation.presenter.impl;

import com.marcohc.architecture.app.domain.interactor.IsFirstTimeInTheAppUseCase;
import com.marcohc.architecture.app.domain.interactor.IsUserLoggedInUseCase;
import com.marcohc.architecture.app.presentation.presenter.BasePresenter;
import com.marcohc.architecture.app.presentation.presenter.inter.StartPresenter;
import com.marcohc.architecture.app.presentation.view.inter.StartView;

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
