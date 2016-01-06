package com.marcohc.android.clean.architecture.presentation.view.impl.adapter;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.marcohc.android.clean.architecture.domain.model.UserModel;
import com.marcohc.android.clean.architecture.sample.R;
import com.marcohc.android.clean.architecture.presentation.view.impl.fragment.MapClusterRenderer;

import java.lang.ref.WeakReference;

public class MapInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

    private WeakReference<Activity> activity;
    private WeakReference<MapClusterRenderer> storeClusterRenderer;

    public MapInfoWindowAdapter(Activity activity, MapClusterRenderer storeClusterRenderer) {
        this.activity = new WeakReference<>(activity);
        this.storeClusterRenderer = new WeakReference<>(storeClusterRenderer);
    }

    public View getInfoWindow(Marker marker) {
        View view = activity.get().getLayoutInflater().inflate(R.layout.map_info_window_layout, null);
        TextView usernameText = (TextView) view.findViewById(R.id.nameText);
//        TextView pointsText = (TextView) view.findViewById(R.id.pointsText);
        UserModel storeClusterItemModel = storeClusterRenderer.get().getClusterItem(marker);
        usernameText.setText(storeClusterItemModel.getName().getFirst());
        return view;
    }

    @Override
    public View getInfoContents(final Marker marker) {
        return null;
    }
}