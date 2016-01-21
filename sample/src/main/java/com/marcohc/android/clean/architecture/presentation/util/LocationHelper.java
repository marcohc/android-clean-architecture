package com.marcohc.android.clean.architecture.presentation.util;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;

import com.afollestad.materialdialogs.MaterialDialog;
import com.marcohc.android.clean.architecture.sample.R;
import com.marcohc.helperoid.DialogHelper;

import timber.log.Timber;

public class LocationHelper {

    public static final long MAP_REFRESH_THRESHOLD = 5 * 60 * 1000;
    private static Location lastKnownLocation;

    public static void showNoGPSAlertDialog(final Context context) {
        DialogHelper.showConfirmationDialog(R.string.gps_question, R.string.yes, R.string.no, context, new MaterialDialog.ButtonCallback() {
            @Override
            public void onPositive(MaterialDialog dialog) {
                super.onPositive(dialog);
                context.startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }

            @Override
            public void onNegative(MaterialDialog dialog) {
                super.onNegative(dialog);
                dialog.dismiss();
            }
        });
    }

    /**
     * try to get the 'best' location selected from all providers
     */
    public static Location getLastKnownLocation(Context context) {
        Location gpslocation = getLocationByProvider(context, LocationManager.GPS_PROVIDER);
        Location networkLocation = getLocationByProvider(context, LocationManager.NETWORK_PROVIDER);
        // if we have only one location available, the choice is easy
        if (gpslocation == null) {
            Timber.d("No GPS Localization available.");
            lastKnownLocation = networkLocation;
            return networkLocation;
        }
        if (networkLocation == null) {
            Timber.d("No Network Localization available");
            lastKnownLocation = gpslocation;
            return gpslocation;
        }
        // a locationupdate is considered 'old' if its older than the configured
        // update interval. this means, we didn't get a
        // update from this provider since the last check
        long old = System.currentTimeMillis() - MAP_REFRESH_THRESHOLD;
        boolean gpsIsOld = (gpslocation.getTime() < old);
        boolean networkIsOld = (networkLocation.getTime() < old);
        // gps is current and available, gps is better than network
        if (!gpsIsOld) {
            Timber.d("Returning current GPS Localization");
            lastKnownLocation = gpslocation;
            return gpslocation;
        }
        // gps is old, we can't trust it. use network location
        if (!networkIsOld) {
            Timber.d("GPS is old, Network is current, returning network");
            lastKnownLocation = networkLocation;
            return networkLocation;
        }
        // both are old return the newer of those two
        if (gpslocation.getTime() > networkLocation.getTime()) {
            Timber.d("Both are old, returning gps(newer)");
            lastKnownLocation = gpslocation;
            return gpslocation;
        } else {
            Timber.d("Both are old, returning network(newer)");
            lastKnownLocation = networkLocation;
            return networkLocation;
        }
    }

    /**
     * get the last known location from a specific provider (network/gps)
     */
    private static Location getLocationByProvider(Context context, String provider) {
        Location location = null;
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        try {
            if (locationManager.isProviderEnabled(provider)) {
                location = locationManager.getLastKnownLocation(provider);
            }
        } catch (IllegalArgumentException e) {
            Timber.d("Cannot access Provider " + provider);
        }
        return location;
    }

    public static Location getLatestLocation() {
        return lastKnownLocation;
    }
}
