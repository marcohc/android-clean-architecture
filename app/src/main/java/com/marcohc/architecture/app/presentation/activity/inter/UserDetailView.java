package com.marcohc.architecture.app.presentation.activity.inter;

import com.marcohc.architecture.app.domain.model.UserModel;
import com.marcohc.architecture.presentation.view.BaseView;

public interface UserDetailView extends BaseView {

    void setUserData(UserModel user);
}
