package com.marcohc.android.clean.architecture.presentation.notification;

/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.app.ActivityManager;
import android.app.IntentService;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.marcohc.android.clean.architecture.common.bus.BusProvider;

import java.util.List;

/**
 * This {@code IntentService} does the actual handling of the GCM message. {@code GcmBroadcastReceiver} (a {@code WakefulBroadcastReceiver}) holds a partial wake lock for this service while the service does its work. When the service is finished, it
 * calls {@code completeWakefulIntent()} to release the wake lock.
 */
public class NotificationIntentService extends IntentService {

    // ************************************************************************************************************************************************************************
    // * Constants
    // ************************************************************************************************************************************************************************

    public static final String LOG_TAG = "Notifications";

    // ************************************************************************************************************************************************************************
    // * Attributes
    // ************************************************************************************************************************************************************************

    // ************************************************************************************************************************************************************************
    // * Initialization methods
    // ************************************************************************************************************************************************************************

    public NotificationIntentService() {
        super("GcmIntentService");
    }

    // ************************************************************************************************************************************************************************
    // * Event handler methods
    // ************************************************************************************************************************************************************************

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i(LOG_TAG, "NotificationIntentService: Notification received");
        Bundle extras = intent.getExtras();
        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);
        String messageType = gcm.getMessageType(intent);
        if (!extras.isEmpty()) {
            if (GoogleCloudMessaging.MESSAGE_TYPE_SEND_ERROR.equals(messageType)) {
                onReceiveNotification(extras);
            } else if (GoogleCloudMessaging.MESSAGE_TYPE_DELETED.equals(messageType)) {
                onReceiveNotification(extras);
            } else if (GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE.equals(messageType)) {
                onReceiveNotification(extras);
                Log.d(LOG_TAG, "Received: " + extras.toString());
            }
        }
        NotificationBroadcastReceiver.completeWakefulIntent(intent);
    }

    private void onReceiveNotification(final Bundle extras) {

        Log.d(LOG_TAG, "NotificationIntentService.onReceiveNotification");

        Handler handler = new Handler(Looper.getMainLooper());
        Runnable runner = new Runnable() {
            @Override
            public void run() {
                handleNotification(extras);
            }
        };
        handler.post(runner);
    }

    // ************************************************************************************************************************************************************************
    // * Action methods
    // ************************************************************************************************************************************************************************

    private void handleNotification(Bundle extras) {

        ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(1);
        ComponentName componentInfo = taskInfo.get(0).topActivity;

        BusProvider.getInstance().register(this);

//        if (componentInfo.getClassName().equals(MainActivity.class.getName())) {
//        }

        BusProvider.getInstance().unregister(this);
    }
}