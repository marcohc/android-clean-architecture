package com.marcohc.architecture.app.presentation.story.user.userdetail.view;

import android.app.Activity;

import com.marcohc.architecture.app.domain.model.UserModel;
import com.marcohc.architecture.app.presentation.navigation.Navigator;
import com.marcohc.architecture.presentation.presenter.BaseMvpPresenter;

import dagger.internal.Preconditions;

/**
 * Presenter implementation of {@link UserDetailPresenter}.
 *
 * @author Marco Hernaiz
 * @since 08/08/16
 */
class UserDetailPresenterImpl extends BaseMvpPresenter<UserDetailView> implements UserDetailPresenter {

    // ************************************************************************************************************************************************************************
    // * View handler methods
    // ************************************************************************************************************************************************************************

    @Override
    public void onViewCreated(Activity activity) {
        Preconditions.checkNotNull(activity, "activity");

        UserModel user = (UserModel) activity.getIntent().getSerializableExtra(Navigator.USER);
        getView().renderModel(user);
    }

}
