package com.marcohc.architecture.app.presentation.presenter.inter;

import com.marcohc.architecture.app.presentation.fragment.inter.UsersListView;
import com.marcohc.architecture.presentation.mosby.mvp.MvpPresenter;

public interface UsersListPresenter extends MvpPresenter<UsersListView> {

    void onViewCreated();

    void onRefresh();
}
