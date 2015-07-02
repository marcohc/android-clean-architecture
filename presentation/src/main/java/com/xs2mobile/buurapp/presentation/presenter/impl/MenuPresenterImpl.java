package com.xs2mobile.buurapp.presentation.presenter.impl;

import com.xs2mobile.buurapp.domain.interactor.impl.MenuInteractorImpl;
import com.xs2mobile.buurapp.domain.interactor.inter.MenuInteractor;
import com.xs2mobile.buurapp.presentation.util.PreferencesManager;
import com.xs2mobile.buurapp.presentation.presenter.BasePresenter;
import com.xs2mobile.buurapp.presentation.presenter.inter.MenuPresenter;
import com.xs2mobile.buurapp.presentation.view.inter.MenuView;

public class MenuPresenterImpl extends BasePresenter<MenuView, MenuInteractor> implements MenuPresenter {

    @Override
    public MenuInteractor createInteractor() {
        return new MenuInteractorImpl(this);
    }

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
