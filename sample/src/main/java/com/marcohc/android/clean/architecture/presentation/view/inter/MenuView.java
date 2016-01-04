package com.marcohc.android.clean.architecture.presentation.view.inter;

import com.marcohc.android.clean.architecture.presentation.view.BaseView;

public interface MenuView extends BaseView {

    void goToLogin();

    void setSelectedMenuItem(int position);
}
