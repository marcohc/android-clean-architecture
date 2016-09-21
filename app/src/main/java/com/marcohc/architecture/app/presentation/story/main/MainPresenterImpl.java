package com.marcohc.architecture.app.presentation.story.main;

import com.marcohc.architecture.app.domain.bus.event.MenuItemClickEvent;
import com.marcohc.architecture.app.domain.bus.event.MenuSelectItemEvent;
import com.marcohc.architecture.common.bus.BusProvider;
import com.marcohc.architecture.presentation.presenter.BaseMvpPresenter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

@SuppressWarnings("ConstantConditions")
public class MainPresenterImpl extends BaseMvpPresenter<MainView> implements MainPresenter {

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
        getView().onMenuItemClick(event.getPosition());
    }
}
