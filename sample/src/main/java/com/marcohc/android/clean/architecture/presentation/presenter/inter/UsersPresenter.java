package com.marcohc.android.clean.architecture.presentation.presenter.inter;

import com.marcohc.android.clean.architecture.presentation.mosby.mvp.MvpPresenter;
import com.marcohc.android.clean.architecture.domain.model.UserModel;
import com.marcohc.android.clean.architecture.presentation.view.inter.UsersView;

public interface UsersPresenter extends MvpPresenter<UsersView> {

    void onViewCreated();

    UserModel getUser();

}
