package com.xs2mobile.buurapp.presentation.notification;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.NotificationCompat;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.squareup.otto.Subscribe;
import com.xs2mobile.buurapp.R;
import com.xs2mobile.buurapp.data.datasource.DataError;
import com.xs2mobile.buurapp.data.datasource.rest.RepositoryCallback;
import com.xs2mobile.buurapp.domain.repository.MessageRepository;
import com.xs2mobile.buurapp.domain.repository.UserRepository;
import com.xs2mobile.buurapp.domain.bus.BusProvider;
import com.xs2mobile.buurapp.domain.bus.event.CreateNotificationEvent;
import com.xs2mobile.buurapp.presentation.model.MessageModel;
import com.xs2mobile.buurapp.presentation.model.UserModel;
import com.xs2mobile.buurapp.presentation.util.PreferencesManager;
import com.xs2mobile.buurapp.presentation.view.impl.activity.MainActivity;

import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

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
                    sendRegistrationToBackend(registrationId);
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
                sendUnregisterToBackend(token);
            }
        }).start();
    }

    private void sendRegistrationToBackend(String registrationId) {

        UserModel user = UserRepository.getInstance().getCurrentUser();
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String uuid = telephonyManager.getDeviceId();

        HashMap<String, String> map = new HashMap<>();
        map.put("device_id", uuid);
        map.put("registration_id", registrationId);
        map.put("device_type", "android");
        MessageRepository.getInstance().registerToPushNotifications(map, user.getToken(), new RepositoryCallback() {
            @Override
            public void failure(DataError error) {
                Log.e(LOG_TAG, "Error when registering device: " + error.getMessage());
            }

            @Override
            public void success(Object response) {
                DataError error = DataError.getError((JSONObject) response);
                if (error == null) {
                    PreferencesManager.saveRegistrationId(NotificationManager.registrationId);
                } else {
                    failure(error);
                }
            }
        });
    }

    private void sendUnregisterToBackend(final String token) {

        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String uuid = telephonyManager.getDeviceId();

        HashMap<String, Object> map = new HashMap<>();
        map.put("device_id", uuid);
        map.put("device_type", "android");
        map.put("status", "false");
        MessageRepository.getInstance().unregisterFromPushNotifications(map, token, new RepositoryCallback() {
            @Override
            public void failure(DataError error) {
                Log.e(LOG_TAG, "Error when registering device: " + error.getMessage());
            }

            @Override
            public void success(Object response) {
                DataError error = DataError.getError((JSONObject) response);
                if (error == null) {
                    PreferencesManager.removeRegistrationId();
                } else {
                    failure(error);
                }
            }
        });
    }

    // ************************************************************************************************************************************************************************
    // * Bus events methods
    // ************************************************************************************************************************************************************************

    @Subscribe
    public void onCreateNotificationEvent(CreateNotificationEvent createNotificationEvent) {
        displayMessageInNotificationTray(createNotificationEvent);
    }

    // ************************************************************************************************************************************************************************
    // * Notification in app methods
    // ************************************************************************************************************************************************************************

    private void displayMessageInNotificationTray(CreateNotificationEvent event) {

        MessageModel message = event.getMessage();
        Long pushId = System.currentTimeMillis();
        android.app.NotificationManager notificationManager = (android.app.NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Intent intent = new Intent(context.getApplicationContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        intent.putExtra(MESSAGE, message.toJsonString());
        intent.putExtra(PUSH_TAG, NOTIFICATION_KEY.MESSAGE_RECEIVED);
        intent.putExtra(PUSH_UNIQUE_ID_TAG, pushId + "");
        intent.putExtra(MESSAGE_TYPE, event.getType());

        PendingIntent contentIntent = PendingIntent.getActivity(context, pushId.intValue(), intent, PendingIntent.FLAG_CANCEL_CURRENT);
        final NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setSmallIcon(R.drawable.ic_logo_notification);
        builder.setContentTitle(message.getUser().getFirstName());
        builder.setContentText(message.getText());
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
