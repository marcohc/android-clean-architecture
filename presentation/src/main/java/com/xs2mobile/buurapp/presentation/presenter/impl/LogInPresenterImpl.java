package com.xs2mobile.buurapp.presentation.presenter.impl;

import com.xs2mobile.buurapp.domain.interactor.impl.LogInInteractorImpl;
import com.xs2mobile.buurapp.domain.interactor.inter.LogInInteractor;
import com.xs2mobile.buurapp.presentation.presenter.BasePresenter;
import com.xs2mobile.buurapp.presentation.presenter.inter.LogInPresenter;
import com.xs2mobile.buurapp.presentation.view.inter.LogInView;

import org.apache.commons.lang3.StringUtils;

public class LogInPresenterImpl extends BasePresenter<LogInView, LogInInteractor> implements LogInPresenter {

    @Override
    public LogInInteractor createInteractor() {
        return new LogInInteractorImpl(this);
    }

    // ************************************************************************************************************************************************************************
    // * View handler methods
    // ************************************************************************************************************************************************************************

    @Override
    public void onForgotPasswordTextClick() {
        getView().goToForgottenPassword();
    }

    @Override
    public void onActionDoneClick() {
        if (isValidaForm()) {
            getView().showLoading(true);
            getInteractor().logIn(getView().getUsername(), getView().getPassword());
        }
    }

    // ************************************************************************************************************************************************************************
    // * Interactor handler methods
    // ************************************************************************************************************************************************************************

    @Override
    public void goToMain() {
        getView().goToMain();
    }

    // ************************************************************************************************************************************************************************
    // * Presenter methods
    // ************************************************************************************************************************************************************************

    private boolean isValidaForm() {

        if (StringUtils.isBlank(getView().getUsername())) {
            getView().invalidateUsername();
            return false;
        }

        if (StringUtils.isBlank(getView().getPassword())) {
            getView().invalidatePassword();
            return false;
        }

        return true;
    }

}
