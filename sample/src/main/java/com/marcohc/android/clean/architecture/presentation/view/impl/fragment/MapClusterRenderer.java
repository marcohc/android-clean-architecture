package com.marcohc.android.clean.architecture.presentation.view.impl.fragment;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;
import com.google.maps.android.ui.IconGenerator;
import com.marcohc.android.clean.architecture.domain.model.UserModel;
import com.marcohc.android.clean.architecture.presentation.R;
import com.marcohc.helperoid.StringHelper;

import java.util.Hashtable;

public class MapClusterRenderer extends DefaultClusterRenderer<UserModel> {

//    private final ImageView clusterItemImage;
//    private final ImageView clusterImage;
//    private final Bitmap mIcon;
//    private final int imageSize;
//    private final TextView clusterNumberText;
    private Activity activity;

    private Hashtable<String, Marker> markersMap;
    private IconGenerator clusterIconGenerator;
//    private final IconGenerator clusterItemIconGenerator;

    public MapClusterRenderer(Activity activity, GoogleMap map, ClusterManager<UserModel> clusterManager) {
        super(activity, map, clusterManager);
        this.activity = activity;
//        mIcon = BitmapFactory.decodeResource(activity.getResources(), clusterImageId);
        markersMap = new Hashtable<>();

//        imageSize = (int) activity.getResources().getDimension(R.dimen.image_big);

        // Cluster
        RelativeLayout clusterItemLayout = (RelativeLayout) ((LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.map_cluster_layout, null);
        clusterIconGenerator = new IconGenerator(activity);
        clusterIconGenerator.setContentView(clusterItemLayout);
        // Very important to remove the indicator background
//        clusterIconGenerator.setBackground(null);
//        clusterImage = (ImageView) clusterItemLayout.findViewById(R.id.clusterImage);
//        clusterNumberText = (TextView) clusterItemLayout.findViewById(R.id.clusterNumberText);

        // Item
//        RelativeLayout mapItemLayout = (RelativeLayout) ((LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.map_cluster_item_layout, null);
        //        clusterItemIconGenerator = new IconGenerator(activity);
//        clusterItemIconGenerator.setContentView(mapItemLayout);
//        clusterItemImage = (ImageView) mapItemLayout.findViewById(R.id.clusterItemImage);

    }

    /**
     * Icons for each bucket.
     */
    private SparseArray<BitmapDescriptor> mIcons = new SparseArray<>();

    @Override
    protected void onBeforeClusterRendered(Cluster<UserModel> cluster, MarkerOptions markerOptions) {
        Bitmap icon = clusterIconGenerator.makeIcon();
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(icon));
    }

    @Override
    protected void onClusterRendered(Cluster<UserModel> cluster, Marker marker) {
        super.onClusterRendered(cluster, marker);
        for (UserModel item : cluster.getItems()) {
            markersMap.put(item.getUsername(), marker);
        }
    }

    @Override
    protected void onBeforeClusterItemRendered(UserModel item, final MarkerOptions markerOptions) {
        String imageUrl = item.getPicture().getThumbnail();
        if (!StringHelper.isEmpty(imageUrl)) {
            Glide.with(activity).load(imageUrl).error(R.drawable.im_user_place_holder).into(new Target() {
                @Override
                public void onStart() {

                }

                @Override
                public void onStop() {

                }

                @Override
                public void onDestroy() {

                }

                @Override
                public void onLoadStarted(Drawable placeholder) {
//                    markerOptions.icon(BitmapDescriptorFactory.fromBitmap(placeholder));
                }

                @Override
                public void onLoadFailed(Exception e, Drawable errorDrawable) {

                }

                @Override
                public void onResourceReady(Object resource, GlideAnimation glideAnimation) {

                }

                @Override
                public void onLoadCleared(Drawable placeholder) {

                }

                @Override
                public void getSize(SizeReadyCallback cb) {

                }

                @Override
                public void setRequest(Request request) {

                }

                @Override
                public Request getRequest() {
                    return null;
                }
            });
        }
    }

    @Override
    protected void onClusterItemRendered(UserModel clusterItem, final Marker marker) {
        super.onClusterItemRendered(clusterItem, marker);
        markersMap.put(clusterItem.getUsername(), marker);
    }

    @Override
    protected boolean shouldRenderAsCluster(Cluster cluster) {
        // Always render clusters.
        return cluster.getSize() > 1;
    }

}
