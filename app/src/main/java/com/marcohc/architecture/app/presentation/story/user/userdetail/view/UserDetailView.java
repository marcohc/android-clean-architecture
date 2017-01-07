package com.marcohc.architecture.app.presentation.story.user.userdetail.view;

import android.support.annotation.NonNull;

import com.marcohc.architecture.app.domain.model.UserModel;
import com.marcohc.architecture.aca.presentation.mvp.BaseMvpView;

/**
 * View which displays the detail of a {@link UserModel}.
 *
 * @author Marco Hernaiz
 * @since 08/08/16
 */
interface UserDetailView extends BaseMvpView {

    void renderModel(@NonNull UserModel user);
}
