package com.marcohc.architecture.presentation.presenter.inter;

import com.marcohc.architecture.presentation.mosby.mvp.MvpPresenter;
import com.marcohc.architecture.presentation.view.inter.UserDetailView;

public interface UserDetailPresenter extends MvpPresenter<UserDetailView> {

    void onViewCreated(String userJson);

}