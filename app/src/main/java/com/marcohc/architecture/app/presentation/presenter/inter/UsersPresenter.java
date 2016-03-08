package com.marcohc.architecture.app.presentation.presenter.inter;

import com.marcohc.architecture.presentation.mosby.mvp.MvpPresenter;
import com.marcohc.architecture.app.domain.model.UserModel;
import com.marcohc.architecture.app.presentation.view.inter.UsersView;

public interface UsersPresenter extends MvpPresenter<UsersView> {

    void onViewCreated();

    UserModel getUser();

}
