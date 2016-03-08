package com.marcohc.architecture.app.presentation.presenter.inter;

import com.marcohc.architecture.presentation.mosby.mvp.MvpPresenter;
import com.marcohc.architecture.app.presentation.view.inter.UserDetailView;

public interface UserDetailPresenter extends MvpPresenter<UserDetailView> {

    void onViewCreated(String userJson);

}
