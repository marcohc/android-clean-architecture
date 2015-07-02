package com.xs2mobile.buurapp.presentation.view.inter;

public interface LogInView extends BaseView {

    void goToForgottenPassword();

    String getUsername();

    String getPassword();

    void goToMain();

    void invalidateUsername();

    void invalidatePassword();

}
