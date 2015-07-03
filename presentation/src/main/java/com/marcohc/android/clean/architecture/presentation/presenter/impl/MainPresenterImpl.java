package com.marcohc.android.clean.architecture.presentation.presenter.impl;

import com.marcohc.android.clean.architecture.common.bus.response.SendMessageResponse;
import com.marcohc.android.clean.architecture.common.exception.DataException;
import com.marcohc.android.clean.architecture.domain.interactor.impl.SendMessageUseCase;
import com.marcohc.android.clean.architecture.presentation.presenter.BasePresenter;
import com.marcohc.android.clean.architecture.presentation.presenter.inter.MainPresenter;
import com.marcohc.android.clean.architecture.presentation.view.inter.MainView;
import com.squareup.otto.Subscribe;

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

    @Subscribe
    public void onSendMessageResponse(SendMessageResponse response) {

    }

    @Subscribe
    public void onException(DataException exception) {
        handleException(exception);
    }
}
