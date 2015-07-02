package com.xs2mobile.buurapp.presentation.presenter.impl;

import com.xs2mobile.buurapp.domain.interactor.impl.ProfileInteractorImpl;
import com.xs2mobile.buurapp.domain.interactor.inter.ProfileInteractor;
import com.xs2mobile.buurapp.presentation.model.UserModel;
import com.xs2mobile.buurapp.presentation.presenter.BasePresenter;
import com.xs2mobile.buurapp.presentation.presenter.inter.ProfilePresenter;
import com.xs2mobile.buurapp.presentation.view.inter.ProfileView;

public class ProfilePresenterImpl extends BasePresenter<ProfileView, ProfileInteractor> implements ProfilePresenter {

    @Override
    public ProfileInteractor createInteractor() {
        return new ProfileInteractorImpl(this);
    }

    // ************************************************************************************************************************************************************************
    // * View handler methods
    // ************************************************************************************************************************************************************************

    @Override
    public void onEditProfileButtonClick() {
        getView().goToProfileDetail();
    }

    @Override
    public UserModel getUser() {
        return getInteractor().getUser();
    }
}
