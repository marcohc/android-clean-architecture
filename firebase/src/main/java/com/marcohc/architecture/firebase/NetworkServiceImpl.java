package com.marcohc.architecture.firebase;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

class NetworkServiceImpl implements NetworkService {

    private static final Boolean LOCK = false;
    private final Context context;

    NetworkServiceImpl(Context context) {
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
