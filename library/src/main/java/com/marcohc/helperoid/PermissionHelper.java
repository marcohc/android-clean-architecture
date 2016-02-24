package com.marcohc.helperoid;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import com.marcohc.architecture.R;

import permissions.dispatcher.PermissionRequest;

public class PermissionHelper {

    public static void showRationaleDialog(Context context, @StringRes int messageResId, final PermissionRequest request) {
        new AlertDialog.Builder(context)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(@NonNull DialogInterface dialog, int which) {
                        request.proceed();
                    }
                })
                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(@NonNull DialogInterface dialog, int which) {
                        request.cancel();
                    }
                })
                .setCancelable(false)
                .setTitle(R.string.permissions)
                .setMessage(messageResId)
                .show();
    }

    public static void showSettingsDialog(final Context context) {
        new AlertDialog.Builder(context)
                .setPositiveButton(R.string.settings, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(@NonNull DialogInterface dialog, int which) {
                        NavigationHelper.goToSettings(context);
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(@NonNull DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setCancelable(false)
                .setTitle(R.string.permission_denied)
                .setMessage(R.string.permission_settings)
                .show();
    }
}
