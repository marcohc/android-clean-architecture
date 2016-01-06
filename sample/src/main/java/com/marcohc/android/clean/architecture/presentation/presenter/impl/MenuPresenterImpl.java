package com.marcohc.android.clean.architecture.presentation.presenter.impl;

import com.marcohc.android.clean.architecture.common.bus.BusProvider;
import com.marcohc.android.clean.architecture.domain.bus.event.MenuItemClickEvent;
import com.marcohc.android.clean.architecture.domain.bus.event.MenuSelectItemEvent;
import com.marcohc.android.clean.architecture.domain.bus.response.domain.LogOutDomainResponse;
import com.marcohc.android.clean.architecture.domain.interactor.LogOutUseCase;
import com.marcohc.android.clean.architecture.presentation.BasePresenter;
import com.marcohc.android.clean.architecture.presentation.presenter.inter.MenuPresenter;
import com.marcohc.android.clean.architecture.presentation.view.inter.MenuView;

@SuppressWarnings("ConstantConditions")
public class MenuPresenterImpl extends BasePresenter<MenuView> implements MenuPresenter {

    // ************************************************************************************************************************************************************************
    // * View handler methods
    // ************************************************************************************************************************************************************************

    @Override
    public void onMenuItemClick(int position) {
        BusProvider.post(new MenuItemClickEvent(position));
    }

    @Override
    public void onLogOutContainerClick() {
        new LogOutUseCase().execute();
    }

    // ************************************************************************************************************************************************************************
    // * Presenter methods
    // ************************************************************************************************************************************************************************

    public void onEventMainThread(MenuSelectItemEvent event) {
        if (isViewAttached()) {
            getView().setSelectedMenuItem(event.getPosition());
        }
    }

    public void onEventMainThread(LogOutDomainResponse event) {
        if (isViewAttached()) {
            getView().gotToAuthentication();
        }
    }
}
