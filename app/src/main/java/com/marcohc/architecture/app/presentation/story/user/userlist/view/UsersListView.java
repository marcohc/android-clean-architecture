package com.marcohc.architecture.app.presentation.story.user.userlist.view;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.marcohc.architecture.app.domain.model.UserModel;
import com.marcohc.architecture.aca.presentation.mvp.BaseMvpView;

import java.util.List;

/**
 * View interface which displays random users in a list with different display options.
 *
 * @author Marco Hernaiz
 * @since 08/08/16
 */
interface UsersListView extends BaseMvpView {

    void enableCancelButton(boolean enable);

    void goToUserDetail(@NonNull UserModel model);

    void hideDialog();

    void renderModelList(@Nullable List<UserModel> modelList);

    void setTimeSpent(@NonNull Long timeSpentInMilliseconds);

    void showLoadingDialog();

    void showMessage(String format);

    void showRefreshSpinner(boolean show);
}
