package com.marcohc.android.clean.architecture.presentation.notification;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.marcohc.android.clean.architecture.common.bus.BusProvider;
import com.marcohc.android.clean.architecture.domain.bus.event.request.NotificationReceivedEvent;
import com.marcohc.android.clean.architecture.presentation.R;
import com.marcohc.android.clean.architecture.presentation.view.impl.activity.MainActivity;

import java.io.IOException;

public class NotificationManager {

    // ************************************************************************************************************************************************************************
    // * Constants
    // ************************************************************************************************************************************************************************

    public static final String MESSAGE = "message";
    public static final String PUSH_TAG = "pushType";
    public static final String PUSH_UNIQUE_ID_TAG = "push_unique_id";
    private final static String APPLICATION_GCM_ID = "226626566963";
    private static final String MESSAGE_TYPE = "message_type";

    public enum NOTIFICATION_KEY {
        MESSAGE_RECEIVED
    }

    // ************************************************************************************************************************************************************************
    // * Attributes
    // ************************************************************************************************************************************************************************

    private static final String LOG_TAG = "NotificationManager";
    private static GoogleCloudMessaging gcm;
    private static String registrationId;
    private static NotificationManager instance;
    private final Context context;

    // ************************************************************************************************************************************************************************
    // * Initialization methods
    // ************************************************************************************************************************************************************************

    public NotificationManager(Context context) {
        this.context = context;
    }

    public static void initialize(Context context) {
        instance = new NotificationManager(context);
        BusProvider.getInstance().register(instance);
    }

    public static NotificationManager getInstance() {
        if (instance == null) {
            throw new ExceptionInInitializerError("Call initialize first!");
        }
        return instance;
    }

    // ************************************************************************************************************************************************************************
    // * Registration Push methods
    // ************************************************************************************************************************************************************************

    public void registerInBackground() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (gcm == null) {
                        gcm = GoogleCloudMessaging.getInstance(context);
                    }
                    registrationId = gcm.register(APPLICATION_GCM_ID);
                } catch (IOException ex) {
                    Log.e(LOG_TAG, ex.getMessage());
                }
            }
        }).start();
    }

    public void unregisterInBackground(final String token) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (gcm != null) {
                    try {
                        gcm.unregister();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    // ************************************************************************************************************************************************************************
    // * Bus events methods
    // ************************************************************************************************************************************************************************

    public void onEvent(NotificationReceivedEvent event) {
        displayMessageInNotificationTray();
    }

    // ************************************************************************************************************************************************************************
    // * Notification in app methods
    // ************************************************************************************************************************************************************************

    private void displayMessageInNotificationTray() {

        Long pushId = System.currentTimeMillis();
        android.app.NotificationManager notificationManager = (android.app.NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Intent intent = new Intent(context.getApplicationContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        intent.putExtra(PUSH_TAG, NOTIFICATION_KEY.MESSAGE_RECEIVED);

        PendingIntent contentIntent = PendingIntent.getActivity(context, pushId.intValue(), intent, PendingIntent.FLAG_CANCEL_CURRENT);
        final NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setSmallIcon(R.drawable.abc_ratingbar_full_material);
        builder.setContentTitle("");
        builder.setContentText("");
        Uri notificationSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        builder.setSound(notificationSound);
        builder.setAutoCancel(true);
        builder.setAutoCancel(true);
        builder.setContentIntent(contentIntent);
        notificationManager.notify(getNotificationId(), builder.build());
    }

    private static int getNotificationId() {
        long time = new java.util.Date().getTime();
        String tmpStr = String.valueOf(time);
        String last4Str = tmpStr.substring(tmpStr.length() - 5);
        return Integer.valueOf(last4Str);
    }

}
