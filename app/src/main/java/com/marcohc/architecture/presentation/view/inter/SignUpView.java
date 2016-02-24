package com.marcohc.architecture.presentation.view.inter;

import com.marcohc.architecture.presentation.view.BaseView;

public interface SignUpView extends BaseView {

    String getFirstName();

    String getLastName();

    String getEmail();

    String getPassword();

    String getConfirmPassword();

    void goToMain();

    void invalidateFirstName();

    void invalidateLastName();

    void invalidateEmail();

    void invalidatePassword();

    void invalidateConfirmPassword();

}
