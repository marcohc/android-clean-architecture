package com.marcohc.architecture.presentation.view.inter;

import com.marcohc.architecture.domain.model.UserModel;
import com.marcohc.architecture.presentation.view.BaseView;

public interface UserDetailView extends BaseView {

    void setUserData(UserModel user);
}
