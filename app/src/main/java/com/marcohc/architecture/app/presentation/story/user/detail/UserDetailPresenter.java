package com.marcohc.architecture.app.presentation.story.user.detail;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;

interface UserDetailPresenter extends MvpPresenter<UserDetailView> {

    void onViewCreated(String userJson);

}
