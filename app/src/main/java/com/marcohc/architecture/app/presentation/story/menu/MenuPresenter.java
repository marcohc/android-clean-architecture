package com.marcohc.architecture.app.presentation.story.menu;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;

public interface MenuPresenter extends MvpPresenter<MenuView> {

    void onMenuItemClick(int position);

}
