package com.marcohc.architecture.presentation.view.impl.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;
import com.marcohc.architecture.domain.model.UserModel;
import com.marcohc.architecture.sample.R;
import com.marcohc.architecture.presentation.presenter.impl.MapPresenterImpl;
import com.marcohc.architecture.presentation.presenter.inter.MapPresenter;
import com.marcohc.architecture.presentation.util.LocationHelper;
import com.marcohc.architecture.presentation.view.fragment.BaseMvpFragment;
import com.marcohc.architecture.presentation.view.impl.adapter.MapInfoWindowAdapter;
import com.marcohc.architecture.presentation.view.inter.MapView;
import com.marcohc.architecture.common.helper.PermissionHelper;
import com.marcohc.architecture.common.helper.ScreenHelper;

import java.util.Calendar;
import java.util.List;

import butterknife.Bind;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class MyMapFragment extends BaseMvpFragment<MapView, MapPresenter>
        implements MapView, OnMapReadyCallback, LocationSource.OnLocationChangedListener,
        LocationListener, ClusterManager.OnClusterClickListener<UserModel>,
        ClusterManager.OnClusterInfoWindowClickListener<UserModel>, ClusterManager.OnClusterItemClickListener<UserModel>,
        ClusterManager.OnClusterItemInfoWindowClickListener<UserModel> {

    // ************************************************************************************************************************************************************************
    // * Constants
    // ************************************************************************************************************************************************************************

    private static final float ZOOM_WHEN_SEARCHING = 24;
    private static final float ZOOM_WITH_CURRENT_LOCATION = 14;

    // ************************************************************************************************************************************************************************
    // * Attributes
    // ************************************************************************************************************************************************************************

    // Class
    @Bind(R.id.mapFragmentContainer)
    ViewGroup mapFragmentContainer;

    @Bind(R.id.mapContainer)
    ViewGroup mapContainer;

    // View
    private GoogleMap googleMap;
    private LocationManager mLocationManager;
    private MapClusterRenderer clusterRenderer;
    private ClusterManager<UserModel> mClusterManager;
    private int sizeOfClusterItem;
    private SupportMapFragment mapFragment;

    // ************************************************************************************************************************************************************************
    // * Initialization methods
    // ************************************************************************************************************************************************************************

    @Override
    protected int getLayoutRes() {
        return R.layout.map_fragment;
    }

    @Override
    public MapPresenter createPresenter() {
        return new MapPresenterImpl();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstance) {
        super.onViewCreated(view, savedInstance);
        setUpFragments();
        setUpView();
    }

    // Do it like that when fragment inside of fragment
    private void setUpFragments() {
        mapFragment = new SupportMapFragment();
        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.mapFragmentContainer, mapFragment);
        fragmentTransaction.commitAllowingStateLoss();
        mapFragment.getMapAsync(this);
    }

    private void setUpView() {
        setHasOptionsMenu(true);
    }

    private void setUpMap() {

        googleMap.setMyLocationEnabled(true);
        googleMap.getUiSettings().setZoomControlsEnabled(false);
        googleMap.getUiSettings().setMapToolbarEnabled(false);
        mLocationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

        Location location = LocationHelper.getLastKnownLocation(getActivity());
        // Do something with the recent location fix otherwise wait for the update below
        if (location != null && location.getTime() > Calendar.getInstance().getTimeInMillis() - LocationHelper.MAP_REFRESH_THRESHOLD) {
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), ZOOM_WITH_CURRENT_LOCATION));
        } else {
            if (mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
            } else {
                mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
                LocationHelper.showNoGPSAlertDialog(getActivity());
            }
        }
    }


    private void setUpCluster() {

        // Set up cluster manager
        mClusterManager = new ClusterManager<>(getActivity(), googleMap);
        clusterRenderer = new MapClusterRenderer(getActivity(), googleMap, mClusterManager);
        mClusterManager.setRenderer(clusterRenderer);
        mClusterManager.setOnClusterClickListener(this);
        mClusterManager.setOnClusterInfoWindowClickListener(this);
        mClusterManager.setOnClusterItemClickListener(this);
        mClusterManager.setOnClusterItemInfoWindowClickListener(this);

        // Set up map
        googleMap.getUiSettings().setMyLocationButtonEnabled(true);
        googleMap.setOnCameraChangeListener(mClusterManager);
        googleMap.setOnMarkerClickListener(mClusterManager);
        googleMap.setOnInfoWindowClickListener(mClusterManager);
        googleMap.setOnCameraChangeListener(mClusterManager);
        googleMap.setOnMarkerClickListener(mClusterManager);
        googleMap.setInfoWindowAdapter(new MapInfoWindowAdapter(getActivity(), clusterRenderer));
        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng point) {
                ScreenHelper.hideKeyboard(getActivity());
            }
        });

    }

    // ************************************************************************************************************************************************************************
    // * Event handler methods
    // ************************************************************************************************************************************************************************

//    private void onLocationActionButtonClick() {
//        centerCameraOnMyCurrentLocation();
//        ScreenHelper.hideKeyboard(getActivity());
//        mapContainer.requestFocus();
//    }

//    @Override
//    public void onMapReady(GoogleMap googleMap) {
//        this.googleMap = googleMap;
//        setUpMap();
//        setUpCluster();
//        presenter.onMapIsReady(LocationHelper.getLastKnownLocation(getActivity()), sizeOfClusterItem);
//    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        MyMapFragmentPermissionsDispatcher.showMapWithCheck(this);
    }

    @NeedsPermission({Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION})
    protected void showMap() {
        setUpMap();
        setUpCluster();
        presenter.onMapIsReady(LocationHelper.getLastKnownLocation(getActivity()));
    }

    @OnShowRationale({Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION})
    void onShowRationaleForLocation(PermissionRequest request) {
        PermissionHelper.showRationaleDialog(getActivity(), R.string.permission_location_rationale, request);
    }

    @OnNeverAskAgain({Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION})
    void showNeverAskForLocation() {
        PermissionHelper.showSettingsDialog(getActivity());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        MyMapFragmentPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @Override
    public void onLocationChanged(Location location) {
        if (location != null) {
            if (googleMap != null) {
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), ZOOM_WITH_CURRENT_LOCATION));
            }
            mLocationManager.removeUpdates(this);
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    @Override
    public void onProviderEnabled(String provider) {
    }

    @Override
    public void onProviderDisabled(String provider) {
    }

    @Override
    public boolean onClusterClick(Cluster<UserModel> cluster) {
        return true;
    }

    @Override
    public void onClusterInfoWindowClick(Cluster<UserModel> cluster) {
    }

    @Override
    public boolean onClusterItemClick(UserModel storeClusterItemModel) {
        return false;
    }

    @Override
    public void onClusterItemInfoWindowClick(UserModel storeClusterItemModel) {
//        StoreModel store = storeClusterItemModel.getStore();
//        Intent intent = new Intent(getActivity(), StoreActivity.class);
//        intent.putExtra(NavigationManager.STORE, store.toJsonString());
//        getActivity().startActivityForResult(intent, NavigationManager.STORE_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
//                case NavigationManager.STORE_REQUEST_CODE:
//                    Localization location = LocationHelper.getLastKnownLocation(getActivity());
//                    if (location != null) {
//                        presenter.onMapRefresh(location, sizeOfClusterItem);
//                    }
//                    break;
            }
        }
    }

    // ************************************************************************************************************************************************************************
    // * View interface methods
    // ************************************************************************************************************************************************************************

    @Override
    public void setMapItems(List<UserModel> items) {
        if (mClusterManager != null) {
            mClusterManager.clearItems();
            mClusterManager.addItems(items);
            mClusterManager.cluster();
        }
    }

    // ************************************************************************************************************************************************************************
    // * Auxiliary UI methods
    // ************************************************************************************************************************************************************************

    private void centerCameraOnMyCurrentLocation() {
        Location lastLocation = LocationHelper.getLastKnownLocation(getActivity());
        if (lastLocation != null) {
            LatLng myLocation = new LatLng(lastLocation.getLatitude(), lastLocation.getLongitude());
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myLocation, ZOOM_WITH_CURRENT_LOCATION));
        }
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        System.gc();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if (mLocationManager != null) {
            mLocationManager.removeUpdates(this);
        }

        if (googleMap != null) {
            googleMap.clear();
        }

        if (mClusterManager != null) {
            mClusterManager.clearItems();
        }

        mapContainer = null;
        mapFragment = null;
        googleMap = null;
        mClusterManager = null;
        clusterRenderer = null;
        System.gc();
    }

}
