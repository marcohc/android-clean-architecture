package com.xs2mobile.buurapp.presentation.presenter.impl;

import com.xs2mobile.buurapp.domain.interactor.impl.AddTextInteractorImpl;
import com.xs2mobile.buurapp.domain.interactor.inter.AddTextInteractor;
import com.xs2mobile.buurapp.presentation.presenter.BasePresenter;
import com.xs2mobile.buurapp.presentation.presenter.inter.AddTextPresenter;
import com.xs2mobile.buurapp.presentation.view.inter.AddTextView;

public class AddTextPresenterImpl extends BasePresenter<AddTextView, AddTextInteractor> implements AddTextPresenter {

    @Override
    // TODO
    public AddTextInteractor createInteractor() {
        return new AddTextInteractorImpl(this);
    }

    // ************************************************************************************************************************************************************************
    // * View handler methods
    // ************************************************************************************************************************************************************************

}
