package com.marcohc.android.clean.architecture.presentation.view.inter;

import com.marcohc.android.clean.architecture.domain.model.UserModel;
import com.marcohc.android.clean.architecture.presentation.view.BaseView;

public interface UserDetailView extends BaseView {

    void setUserData(UserModel user);
}
