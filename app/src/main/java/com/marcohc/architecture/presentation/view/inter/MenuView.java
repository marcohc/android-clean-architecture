package com.marcohc.architecture.presentation.view.inter;

import com.marcohc.architecture.presentation.view.BaseView;

public interface MenuView extends BaseView {

    void gotToAuthentication();

    void setSelectedMenuItem(int position);
}
