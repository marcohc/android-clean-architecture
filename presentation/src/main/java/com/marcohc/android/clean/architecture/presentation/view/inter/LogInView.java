package com.marcohc.android.clean.architecture.presentation.view.inter;

public interface LogInView extends BaseView {

    String getUsername();

    String getPassword();

    void invalidateUsername();

    void invalidatePassword();

    void goToMain();

}
