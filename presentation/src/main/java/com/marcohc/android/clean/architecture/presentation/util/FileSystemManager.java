package com.marcohc.android.clean.architecture.presentation.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.marcohc.android.clean.architecture.common.util.Constants;
import com.marcohc.helperoid.FileSystemUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileSystemManager extends FileSystemUtils {

    // ************************************************************************************************************************************************************************
    // * Attributes
    // ************************************************************************************************************************************************************************

    public static final String LOG_TAG = "FileSystemManager";

    // Files path
    public static String PHOTOS_LOCAL_PATH;

    // ************************************************************************************************************************************************************************
    // * Initialization methods
    // ************************************************************************************************************************************************************************

    public static void initialize() {

        PHOTOS_LOCAL_PATH = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + File.separator + Constants.LOG_TAG;

        // Create folder structure directories
        initializeLocalDirectories();

        Log.v(LOG_TAG, "PHOTOS_LOCAL_PATH: " + PHOTOS_LOCAL_PATH);
    }

    private static void initializeLocalDirectories() {
        File dir = new File(PHOTOS_LOCAL_PATH);
        dir.mkdirs();
    }

    // ************************************************************************************************************************************************************************
    // * Auxiliary path methods
    // ************************************************************************************************************************************************************************

    @SuppressLint("SimpleDateFormat")
    public static File getPhotoFile() {
        File mediaStorageDir = new File(PHOTOS_LOCAL_PATH);
        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d("MyCameraApp", "failed to create directory");
                return null;
            }
        }
        // Initialize file data
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        return new File(mediaStorageDir.getPath() + File.separator + "IMG_" + timeStamp + ".jpg");
    }

    public static boolean deleteFile(String path) {

        File file = new File(path);

        // Delete local file
        if (file.exists()) {
            return file.delete();
        }
        return false;
    }

    public static boolean openImage(Context context, String path) {
        // Check if this file exits
        File file = new File(path);
        if (file.exists()) {
            showImageProcess(context, file.getPath());
            return true;
        }
        return false;
    }
}
