package com.marcohc.android.clean.architecture.presentation.presenter.inter;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.marcohc.android.clean.architecture.domain.model.UserModel;
import com.marcohc.android.clean.architecture.presentation.view.inter.ProfileView;

public interface ProfilePresenter extends MvpPresenter<ProfileView> {

    void onViewCreated();

    UserModel getUser();

}
