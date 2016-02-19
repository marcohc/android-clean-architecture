package com.marcohc.android.clean.architecture.presentation.presenter.inter;

import com.marcohc.android.clean.architecture.presentation.mosby.mvp.MvpPresenter;
import com.marcohc.android.clean.architecture.presentation.view.inter.MenuView;

public interface MenuPresenter extends MvpPresenter<MenuView> {

    void onLogOutContainerClick();

    void onMenuItemClick(int position);

}
