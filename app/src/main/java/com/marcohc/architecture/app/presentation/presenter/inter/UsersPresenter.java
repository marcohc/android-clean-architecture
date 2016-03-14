package com.marcohc.architecture.app.presentation.presenter.inter;

import com.marcohc.architecture.app.presentation.view.inter.UsersView;
import com.marcohc.architecture.presentation.mosby.mvp.MvpPresenter;

public interface UsersPresenter extends MvpPresenter<UsersView> {

    void onViewCreated();

    void onRefresh();
}
