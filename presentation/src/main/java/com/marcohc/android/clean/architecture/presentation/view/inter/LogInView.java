package com.marcohc.android.clean.architecture.presentation.view.inter;

import com.marcohc.android.clean.architecture.domain.model.UserModel;

public interface LogInView extends BaseView {

    String getUsername();

    String getPassword();

    void invalidateUsername();

    void invalidatePassword();

    void goToMain();

    UserModel getUser();
}
