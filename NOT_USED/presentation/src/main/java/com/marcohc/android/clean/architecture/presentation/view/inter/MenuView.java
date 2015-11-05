package com.marcohc.android.clean.architecture.presentation.view.inter;

public interface MenuView extends BaseView {

    void goToLogin();

    void dispatchMenuItemClick(int position);
}
