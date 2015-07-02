package com.xs2mobile.buurapp.presentation.presenter.inter;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.xs2mobile.buurapp.presentation.model.UserModel;
import com.xs2mobile.buurapp.presentation.view.inter.ProfileView;

public interface ProfilePresenter extends MvpPresenter<ProfileView> {

    void onEditProfileButtonClick();

    UserModel getUser();

}
