package com.marcohc.architecture.app.presentation.story.user.list;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;

interface UsersListPresenter extends MvpPresenter<UsersListView> {

    void onViewCreated();

    void onRefresh();
}
