package com.xs2mobile.buurapp.presentation.presenter.impl;

import com.xs2mobile.buurapp.domain.interactor.impl.StartInteractorImpl;
import com.xs2mobile.buurapp.domain.interactor.inter.StartInteractor;
import com.xs2mobile.buurapp.domain.repository.UserRepository;
import com.xs2mobile.buurapp.presentation.presenter.BasePresenter;
import com.xs2mobile.buurapp.presentation.presenter.inter.StartPresenter;
import com.xs2mobile.buurapp.presentation.view.inter.StartView;

public class StartPresenterImpl extends BasePresenter<StartView, StartInteractor> implements StartPresenter {

    @Override
    public StartInteractor createInteractor() {
        return new StartInteractorImpl(this);
    }

    @Override
    public void onLogInButtonClick() {
        if (isViewAttached()) {
            getView().goToLogIn();
        }
    }

    @Override
    public void onSignUpButtonClick() {
        if (isViewAttached()) {
            getView().goToSignUp();
        }
    }

    @Override
    public boolean isUserLoggedIn() {
        return UserRepository.getInstance().isUserLoggedIn();
    }
}
