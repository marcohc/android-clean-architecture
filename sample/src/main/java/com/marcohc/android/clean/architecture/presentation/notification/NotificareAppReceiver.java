package com.marcohc.android.clean.architecture.presentation.notification;

import android.net.Uri;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import re.notifica.Notificare;
import re.notifica.NotificareCallback;
import re.notifica.NotificareError;
import re.notifica.model.NotificareNotification;
import re.notifica.push.gcm.DefaultIntentReceiver;
import timber.log.Timber;

public class NotificareAppReceiver extends DefaultIntentReceiver {

    @Override
    public void onReady() {
        Notificare.shared().enableNotifications();
        if (Notificare.shared().isLocationUpdatesEnabled()) {
            Notificare.shared().enableLocationUpdates();
        }
    }

    @Override
    public void onNotificationReceived(String alert, String notificationId, Bundle extras) {
        // Execute default behavior, i.e., put notification in drawer
        Timber.d("Notification received with extra " + extras.getString("mykey"));
        super.onNotificationReceived(alert, notificationId, extras);
    }

    @Override
    public void onNotificationOpened(String alert, String notificationId, Bundle extras) {
        // Notification is in extras
        NotificareNotification notification = extras.getParcelable(Notificare.INTENT_EXTRA_NOTIFICATION);
        Timber.d("Notification was opened with type " + (notification != null ? notification.getType() : null));
        Timber.d("Notification was opened with extra " + (notification != null ? notification.getExtra().get("mykey") : null));
        // By default, open the NotificationActivity and let it handle the Notification
        super.onNotificationOpened(alert, notificationId, extras);
    }

    @Override
    public void onRegistrationFinished(String deviceId) {
        Timber.d("Device was registered with GCM as device " + deviceId);

        Notificare.shared().registerDevice(deviceId, new NotificareCallback<String>() {

            @Override
            public void onSuccess(String result) {

                Notificare.shared().fetchDeviceTags(new NotificareCallback<List<String>>() {

                    @Override
                    public void onError(NotificareError arg0) {
                        Timber.e("Error registering device!");
                    }

                    @Override
                    public void onSuccess(List<String> arg0) {

                        ArrayList<String> tags = new ArrayList<String>();
                        tags.add("test_device");

                        Notificare.shared().addDeviceTags(tags, new NotificareCallback<Boolean>() {
                            @Override
                            public void onError(NotificareError error) {
                                Timber.d("Error addDeviceTags!");
                            }

                            @Override
                            public void onSuccess(Boolean success) {
                                Timber.d("Successs addDeviceTags!");
                            }
                        });

                        Timber.d("Successs registering device!");
                    }

                });
            }

            @Override
            public void onError(NotificareError error) {
                Timber.e("Error registering device", error);
            }

        });

    }

    @Override
    public void onActionReceived(Uri target) {
        Timber.d("Custom action was received: " + target.toString());
        // By default, pass the target as data URI to your main activity in a launch intent
        super.onActionReceived(target);
    }

}
