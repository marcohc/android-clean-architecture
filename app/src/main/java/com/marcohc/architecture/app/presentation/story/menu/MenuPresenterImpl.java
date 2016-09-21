package com.marcohc.architecture.app.presentation.story.menu;

import com.marcohc.architecture.common.bus.BusProvider;
import com.marcohc.architecture.app.domain.bus.event.MenuItemClickEvent;
import com.marcohc.architecture.app.domain.bus.event.MenuSelectItemEvent;
import com.marcohc.architecture.presentation.presenter.BaseMvpPresenter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

@SuppressWarnings("ConstantConditions")
public class MenuPresenterImpl extends BaseMvpPresenter<MenuView> implements MenuPresenter {

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
        getView().setSelectedMenuItem(event.getPosition());
    }
}
