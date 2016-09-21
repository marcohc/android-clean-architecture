package com.marcohc.architecture.app.presentation.story.user.userdetail.view;

import android.app.Activity;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.marcohc.architecture.app.domain.model.UserModel;

/**
 * Presenter interface which displays the detail of a {@link UserModel}.
 *
 * @author Marco Hernaiz
 * @since 08/08/16
 */
interface UserDetailPresenter extends MvpPresenter<UserDetailView> {

    void onViewCreated(Activity activity);
}
