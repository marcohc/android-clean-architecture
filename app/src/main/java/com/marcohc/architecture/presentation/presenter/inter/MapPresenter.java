package com.marcohc.architecture.presentation.presenter.inter;

import android.location.Location;

import com.marcohc.architecture.presentation.mosby.mvp.MvpPresenter;
import com.marcohc.architecture.presentation.view.inter.MapView;

public interface MapPresenter extends MvpPresenter<MapView> {

    void onMapIsReady(Location location);

    void onMapRefresh(Location location);

}
