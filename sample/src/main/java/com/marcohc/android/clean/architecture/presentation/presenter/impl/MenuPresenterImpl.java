package com.marcohc.android.clean.architecture.presentation.presenter.impl;

import com.marcohc.android.clean.architecture.common.bus.BusProvider;
import com.marcohc.android.clean.architecture.domain.bus.event.MenuItemClickEvent;
import com.marcohc.android.clean.architecture.domain.bus.event.MenuSelectItemEvent;
import com.marcohc.android.clean.architecture.domain.interactor.LogOutUseCase;
import com.marcohc.android.clean.architecture.presentation.presenter.BasePresenter;
import com.marcohc.android.clean.architecture.presentation.presenter.inter.MenuPresenter;
import com.marcohc.android.clean.architecture.presentation.view.inter.MenuView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
        if (new LogOutUseCase().execute()) {
            getView().gotToAuthentication();
        }
    }

    // ************************************************************************************************************************************************************************
    // * Presenter methods
    // ************************************************************************************************************************************************************************

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(MenuSelectItemEvent event) {
        if (isViewAttached()) {
            getView().setSelectedMenuItem(event.getPosition());
        }
    }
}
