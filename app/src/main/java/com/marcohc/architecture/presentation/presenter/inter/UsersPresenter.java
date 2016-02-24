package com.marcohc.architecture.presentation.presenter.inter;

import com.marcohc.architecture.presentation.mosby.mvp.MvpPresenter;
import com.marcohc.architecture.domain.model.UserModel;
import com.marcohc.architecture.presentation.view.inter.UsersView;

public interface UsersPresenter extends MvpPresenter<UsersView> {

    void onViewCreated();

    UserModel getUser();

}
