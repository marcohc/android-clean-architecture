package com.marcohc.architecture.presentation.presenter.impl;

import com.marcohc.architecture.common.bus.BusProvider;
import com.marcohc.architecture.domain.bus.event.MenuItemClickEvent;
import com.marcohc.architecture.domain.bus.event.MenuSelectItemEvent;
import com.marcohc.architecture.presentation.presenter.BasePresenter;
import com.marcohc.architecture.presentation.presenter.inter.MainPresenter;
import com.marcohc.architecture.presentation.view.inter.MainView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

@SuppressWarnings("ConstantConditions")
public class MainPresenterImpl extends BasePresenter<MainView> implements MainPresenter {

    // ************************************************************************************************************************************************************************
    // * View handler methods
    // ************************************************************************************************************************************************************************

    @Override
    public void onViewCreated() {
    }

    @Override
    public void setSelectedMenuPosition(int position) {
        BusProvider.post(new MenuSelectItemEvent(position));
    }

    // ************************************************************************************************************************************************************************
    // * Presenter methods
    // ************************************************************************************************************************************************************************

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMenuItemClickEvent(MenuItemClickEvent event) {
        if (isViewAttached()) {
            getView().onMenuItemClick(event.getPosition());
        }
    }
}
