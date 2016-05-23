package com.marcohc.architecture.app.presentation.presenter.inter;

import com.marcohc.architecture.app.presentation.activity.inter.UserDetailView;
import com.marcohc.architecture.presentation.mosby.mvp.MvpPresenter;

public interface UserDetailPresenter extends MvpPresenter<UserDetailView> {

    void onViewCreated(String userJson);

}
