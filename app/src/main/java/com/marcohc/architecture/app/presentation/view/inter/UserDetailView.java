package com.marcohc.architecture.app.presentation.view.inter;

import com.marcohc.architecture.app.domain.model.UserModel;
import com.marcohc.architecture.app.presentation.view.BaseView;

public interface UserDetailView extends BaseView {

    void setUserData(UserModel user);
}
