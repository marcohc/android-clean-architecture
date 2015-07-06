package com.marcohc.android.clean.architecture.presentation.presenter.impl;

import com.google.android.gms.wearable.internal.SendMessageResponse;
import com.marcohc.android.clean.architecture.domain.interactor.impl.SendMessageUseCase;
import com.marcohc.android.clean.architecture.presentation.presenter.BasePresenter;
import com.marcohc.android.clean.architecture.presentation.presenter.inter.MainPresenter;
import com.marcohc.android.clean.architecture.presentation.view.inter.MainView;

public class MainPresenterImpl extends BasePresenter<MainView> implements MainPresenter {

    // ************************************************************************************************************************************************************************
    // * View handler methods
    // ************************************************************************************************************************************************************************

    @Override
    public void onViewCreated() {
        new SendMessageUseCase(1l, "Hola").execute();
    }

    // ************************************************************************************************************************************************************************
    // * Presenter methods
    // ************************************************************************************************************************************************************************

    public void onEvent(SendMessageResponse response) {

    }
}
