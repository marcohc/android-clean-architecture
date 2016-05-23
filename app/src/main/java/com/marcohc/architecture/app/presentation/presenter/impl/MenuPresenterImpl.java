package com.marcohc.architecture.app.presentation.presenter.impl;

import com.marcohc.architecture.common.bus.BusProvider;
import com.marcohc.architecture.app.domain.bus.event.MenuItemClickEvent;
import com.marcohc.architecture.app.domain.bus.event.MenuSelectItemEvent;
import com.marcohc.architecture.presentation.presenter.BasePresenter;
import com.marcohc.architecture.app.presentation.presenter.inter.MenuPresenter;
import com.marcohc.architecture.app.presentation.fragment.inter.MenuView;

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
