package com.marcohc.architecture.app.presentation.story.user.userlist.view;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.marcohc.architecture.app.domain.model.UserModel;
import com.marcohc.architecture.presentation.view.BaseMvpView;

import java.util.List;

/**
 * View interface which displays random users in a list with different display options.
 *
 * @author Marco Hernaiz
 * @since 08/08/16
 */
interface UsersListView extends BaseMvpView {

    void renderModelList(@Nullable List<UserModel> modelList);

    void goToUserDetail(@NonNull UserModel model);

    void showRefreshSpinner(boolean show);

    void setTimeSpent(@NonNull Long timeSpentInMilliseconds);

    void enableCancelButton(boolean enable);
}
