package com.marcohc.architecture.presentation.view.inter;

import com.marcohc.architecture.presentation.view.BaseView;

public interface LogInView extends BaseView {

    String getUsername();

    String getPassword();

    void invalidateUsername();

    void invalidatePassword();

    void goToMain();

}
