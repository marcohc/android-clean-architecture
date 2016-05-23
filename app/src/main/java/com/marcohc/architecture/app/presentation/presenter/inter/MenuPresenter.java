package com.marcohc.architecture.app.presentation.presenter.inter;

import com.marcohc.architecture.presentation.mosby.mvp.MvpPresenter;
import com.marcohc.architecture.app.presentation.fragment.inter.MenuView;

public interface MenuPresenter extends MvpPresenter<MenuView> {

    void onMenuItemClick(int position);

}
