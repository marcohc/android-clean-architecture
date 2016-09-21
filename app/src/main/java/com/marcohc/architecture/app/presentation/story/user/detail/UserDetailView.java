package com.marcohc.architecture.app.presentation.story.user.detail;

import com.marcohc.architecture.app.domain.model.UserModel;
import com.marcohc.architecture.presentation.view.BaseView;

interface UserDetailView extends BaseView {

    void setUserData(UserModel user);
}
