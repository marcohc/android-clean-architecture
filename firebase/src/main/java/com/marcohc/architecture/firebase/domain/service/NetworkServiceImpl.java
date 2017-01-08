package com.marcohc.architecture.firebase.domain.service;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkServiceImpl implements NetworkService {

    private static final Boolean LOCK = false;
    private final Context context;

    public NetworkServiceImpl(Context context) {
        this.context = context;
    }

    @Override
    public boolean isOnline() {
        synchronized (LOCK) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
            return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        }
    }
}
