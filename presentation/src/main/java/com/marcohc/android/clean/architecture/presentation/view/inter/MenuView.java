package com.marcohc.android.clean.architecture.presentation.view.inter;

public interface MenuView extends BaseView {

    void goToStart();

    void dispatchMenuItemClick(int position);
}
