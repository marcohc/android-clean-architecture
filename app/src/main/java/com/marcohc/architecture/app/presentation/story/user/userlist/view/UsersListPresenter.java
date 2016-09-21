package com.marcohc.architecture.app.presentation.story.user.userlist.view;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.marcohc.architecture.app.domain.model.UserModel;

/**
 * Presenter interface which displays random users in a list with different display options.
 *
 * @author Marco Hernaiz
 * @since 08/08/16
 */
interface UsersListPresenter extends MvpPresenter<UsersListView> {

    void onViewCreated();

    void onRefresh();

    void onItemClick(UserModel model);

    void onChildViewClick(UserModel model, int position);

    void onUsersDataSourceSwitchChecked(boolean checked);

    void onCacheSwitchChecked(boolean isChecked);

    void onRequestDataButtonClick();

    void onCancelButtonClick();
}
