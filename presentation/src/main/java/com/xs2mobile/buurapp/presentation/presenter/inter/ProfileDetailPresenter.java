package com.xs2mobile.buurapp.presentation.presenter.inter;

import android.content.Intent;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.xs2mobile.buurapp.presentation.model.UserModel;
import com.xs2mobile.buurapp.presentation.view.inter.ProfileDetailView;

public interface ProfileDetailPresenter extends MvpPresenter<ProfileDetailView> {

    void onCreateProfile();

    void onEditProfile();

    void onAddPhotoImageClick();

    void onAddPhotoImageActivityResult(int requestCode, Intent intent);

    UserModel getUser();

    UserModel getUserFromForm();

    void goBack();

    String getUsername();

    String getPassword();
}
