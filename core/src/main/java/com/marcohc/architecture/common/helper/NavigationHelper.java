/*
 * Copyright (C) 2016 Marco Hernaiz Cao
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

package com.marcohc.architecture.common.helper;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.webkit.MimeTypeMap;

import java.io.File;

import timber.log.Timber;

/**
 * Common intent methods
 */
public class NavigationHelper {

    public static void goToAppInGooglePlay(Context context) {
        if (context != null) {
            String appPackageName = context.getPackageName();
            try {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName));
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                context.startActivity(intent);
            } catch (ActivityNotFoundException e) {
                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                Timber.e("goToAppInGooglePlay: ", e);
            }
        }
    }

    public static void goToSettings(Context context) {
        if (context != null) {
            Intent intent = new Intent();
            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            intent.addCategory(Intent.CATEGORY_DEFAULT);
            intent.setData(Uri.parse("package:" + context.getPackageName()));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
            context.startActivity(intent);
        }
    }

    public static void openImage(Context context, String path) {
        if (context != null && !StringHelper.isBlank(path)) {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.parse("file://" + path), "image/*");
            context.startActivity(intent);
        }
    }

    public static void openDocument(Context context, File file) {
        if (context != null && file != null && file.exists()) {
            try {
                MimeTypeMap map = MimeTypeMap.getSingleton();
                String ext = MimeTypeMap.getFileExtensionFromUrl(file.getName());
                String type = map.getMimeTypeFromExtension(ext);

                if (type == null) {
                    type = "*/*";
                }

                Intent intent = new Intent(Intent.ACTION_VIEW);
                Uri data = Uri.fromFile(file);

                intent.setDataAndType(data, type);

                try {
                    context.startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    // Instruct the user to install a PDF reader here, or something
                }
            } catch (Exception e) {
                Timber.e(e, "openDocument");
            }
        }
    }

    public static void shareApp(Context context, String shareText) {
        if (context != null && !StringHelper.isBlank(shareText)) {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, shareText);
            sendIntent.setType("text/plain");
            context.startActivity(sendIntent);
        }
    }

    public static void sendEmail(Context context, String dialogTitle, String[] recipients, String subject, String body, String path) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_EMAIL, recipients);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, body);
        if (path != null) {
            intent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://" + path));
        }

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            context.startActivity(Intent.createChooser(intent, dialogTitle));
        } catch (ActivityNotFoundException ignored) {
        }
    }
}
