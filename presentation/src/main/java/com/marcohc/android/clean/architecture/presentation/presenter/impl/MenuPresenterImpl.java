package com.marcohc.android.clean.architecture.presentation.presenter.impl;

import com.marcohc.android.clean.architecture.presentation.presenter.BasePresenter;
import com.marcohc.android.clean.architecture.presentation.presenter.inter.MenuPresenter;
import com.marcohc.android.clean.architecture.presentation.util.PreferencesManager;
import com.marcohc.android.clean.architecture.presentation.view.inter.MenuView;

public class MenuPresenterImpl extends BasePresenter<MenuView> implements MenuPresenter {

    // ************************************************************************************************************************************************************************
    // * View handler methods
    // ************************************************************************************************************************************************************************

    @Override
    public void onLogOutContainerClick() {
        PreferencesManager.removeUserData();
        getView().goToStart();
    }

    @Override
    public void onMenuItemClick(int position) {
        getView().dispatchMenuItemClick(position);
    }

    // ************************************************************************************************************************************************************************
    // * Presenter methods
    // ************************************************************************************************************************************************************************

}
