package com.marcohc.android.clean.architecture.presentation.presenter.inter;

import android.location.Location;

import com.marcohc.android.clean.architecture.presentation.mosby.mvp.MvpPresenter;
import com.marcohc.android.clean.architecture.presentation.view.inter.MapView;

public interface MapPresenter extends MvpPresenter<MapView> {

    void onMapIsReady(Location location, int sizeOfClusterItem);

    void onMapRefresh(Location location, int sizeOfClusterItem);

}
