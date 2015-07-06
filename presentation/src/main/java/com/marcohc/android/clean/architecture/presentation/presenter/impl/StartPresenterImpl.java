package com.marcohc.android.clean.architecture.presentation.presenter.impl;

import com.marcohc.android.clean.architecture.domain.interactor.impl.IsUserLoggedInUseCase;
import com.marcohc.android.clean.architecture.presentation.presenter.BasePresenter;
import com.marcohc.android.clean.architecture.presentation.presenter.inter.StartPresenter;
import com.marcohc.android.clean.architecture.presentation.view.inter.StartView;

public class StartPresenterImpl extends BasePresenter<StartView> implements StartPresenter {

    @Override
    public boolean isUserLoggedIn() {
        return new IsUserLoggedInUseCase().execute();
    }
}
