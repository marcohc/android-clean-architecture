package com.marcohc.android.clean.architecture.presentation.util;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.marcohc.android.clean.architecture.sample.R;

import timber.log.Timber;

public class LocationHelper {

    public static final long MAP_REFRESH_THRESHOLD = 5 * 60 * 1000;
    private static Location lastKnownLocation;

    public static void showNoGPSAlertDialog(final Context context) {
        new MaterialDialog.Builder(context)
                .title(R.string.gps_question)
                .positiveText(R.string.yes)
                .negativeText(R.string.no)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        context.startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                })
                .show();
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
