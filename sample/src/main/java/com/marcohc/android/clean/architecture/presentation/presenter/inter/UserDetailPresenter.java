package com.marcohc.android.clean.architecture.presentation.presenter.inter;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.marcohc.android.clean.architecture.presentation.view.inter.UserDetailView;

public interface UserDetailPresenter extends MvpPresenter<UserDetailView> {

    void onViewCreated(String userJson);

}
