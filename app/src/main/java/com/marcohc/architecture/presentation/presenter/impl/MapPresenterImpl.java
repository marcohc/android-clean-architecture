package com.marcohc.architecture.presentation.presenter.impl;

import android.location.Location;

import com.marcohc.architecture.domain.bus.response.domain.GetUsersDomainResponse;
import com.marcohc.architecture.domain.interactor.GetUsersUseCase;
import com.marcohc.architecture.presentation.presenter.BasePresenter;
import com.marcohc.architecture.presentation.presenter.inter.MapPresenter;
import com.marcohc.architecture.presentation.view.inter.MapView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

@SuppressWarnings("ConstantConditions")
public class MapPresenterImpl extends BasePresenter<MapView> implements MapPresenter {

    // ************************************************************************************************************************************************************************
    // * View handler methods
    // ************************************************************************************************************************************************************************

    @Override
    public void onMapIsReady(Location location) {
        if (isViewAttached()) {
            onMapRefresh(location);
        }
    }

    @Override
    public void onMapRefresh(Location location) {
        if (isViewAttached()) {
            new GetUsersUseCase().execute();
        }
    }

    // ************************************************************************************************************************************************************************
    // * Interactor handler methods
    // ************************************************************************************************************************************************************************

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(GetUsersDomainResponse event) {
        if (isViewAttached()) {
            getView().setMapItems(event.getUsersList());
        }
    }

}
