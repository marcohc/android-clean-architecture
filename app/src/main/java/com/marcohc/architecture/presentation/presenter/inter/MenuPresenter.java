package com.marcohc.architecture.presentation.presenter.inter;

import com.marcohc.architecture.presentation.mosby.mvp.MvpPresenter;
import com.marcohc.architecture.presentation.view.inter.MenuView;

public interface MenuPresenter extends MvpPresenter<MenuView> {

    void onLogOutContainerClick();

    void onMenuItemClick(int position);

}
