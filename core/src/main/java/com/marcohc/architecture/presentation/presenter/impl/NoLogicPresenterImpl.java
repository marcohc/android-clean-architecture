package com.marcohc.architecture.presentation.presenter.impl;

import com.marcohc.architecture.domain.error.DomainException;
import com.marcohc.architecture.presentation.presenter.BasePresenter;
import com.marcohc.architecture.presentation.presenter.inter.NoLogicPresenter;
import com.marcohc.architecture.presentation.view.inter.NoLogicView;

public class NoLogicPresenterImpl extends BasePresenter<NoLogicView> implements NoLogicPresenter {
    @Override
    public void handleException(DomainException error) {
        // Ignore
    }
}
