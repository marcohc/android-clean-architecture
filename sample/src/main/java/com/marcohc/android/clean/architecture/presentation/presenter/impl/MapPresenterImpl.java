package com.marcohc.android.clean.architecture.presentation.presenter.impl;

import android.location.Location;

import com.marcohc.android.clean.architecture.domain.bus.response.domain.GetUsersDomainResponse;
import com.marcohc.android.clean.architecture.domain.interactor.GetUsersUseCase;
import com.marcohc.android.clean.architecture.presentation.presenter.BasePresenter;
import com.marcohc.android.clean.architecture.presentation.presenter.inter.MapPresenter;
import com.marcohc.android.clean.architecture.presentation.view.inter.MapView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

@SuppressWarnings("ConstantConditions")
public class MapPresenterImpl extends BasePresenter<MapView> implements MapPresenter {

    // ************************************************************************************************************************************************************************
    // * View handler methods
    // ************************************************************************************************************************************************************************

    @Override
    public void onMapIsReady(Location location, int sizeOfClusterItem) {
        if (isViewAttached()) {
            onMapRefresh(location, sizeOfClusterItem);
        }
    }

    @Override
    public void onMapRefresh(Location location, int sizeOfClusterItem) {
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
