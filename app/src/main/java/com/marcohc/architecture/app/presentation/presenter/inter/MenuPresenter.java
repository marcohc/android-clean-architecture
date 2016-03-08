package com.marcohc.architecture.app.presentation.presenter.inter;

import com.marcohc.architecture.app.presentation.mosby.mvp.MvpPresenter;
import com.marcohc.architecture.app.presentation.view.inter.MenuView;

public interface MenuPresenter extends MvpPresenter<MenuView> {

    void onMenuItemClick(int position);

}
