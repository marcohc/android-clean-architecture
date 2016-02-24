/*
 * Copyright (C) 2015 Marco Hernaiz Cao
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

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Base64;

import com.marcohc.architecture.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import timber.log.Timber;

public class ImageHelper {

    // ************************************************************************************************************************************************************************
    // * Show dialog with take picture or get picture from gallery methods
    // ************************************************************************************************************************************************************************

    public static final int SELECT_PICTURE_FROM_GALLERY_REQUEST = 10;
    public static final int CAMERA_REQUEST = 20;

    public static void showPickUpImageDialog(final Activity activity, final Uri file) {
        String[] values = {activity.getString(R.string.camera), activity.getString(R.string.gallery)};
        new AlertDialog.Builder(activity)
                .setTitle(R.string.select)
                .setItems(values, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            pickImageFromCamera(activity, file);
                        } else if (which == 1) {
                            pickImageFromGallery(activity);
                        }
                    }
                })
                .show();
    }

    public static void pickImageFromCamera(Activity activity, Uri file) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, file);
        activity.startActivityForResult(intent, CAMERA_REQUEST);
    }

    public static void pickImageFromGallery(Activity activity) {
        Intent intent;
        if (Build.VERSION.SDK_INT < 19) {
            intent = new Intent(Intent.ACTION_GET_CONTENT, null);
            intent.setType("image/*");
            intent.putExtra("return-data", true);
            activity.startActivityForResult(intent, SELECT_PICTURE_FROM_GALLERY_REQUEST);
        } else {
            intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            activity.startActivityForResult(intent, SELECT_PICTURE_FROM_GALLERY_REQUEST);
        }
    }

    @SuppressLint("NewApi")
    public static String getPath(final Context context, final Uri uri) {

        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{split[1]};

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {
            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }

    /**
     * Get the value of the data column for this Uri. This is useful for MediaStore Uris, and other file-based ContentProviders.
     *
     * @param context       The context.
     * @param uri           The Uri to query.
     * @param selection     (Optional) Filter used in the query.
     * @param selectionArgs (Optional) Selection arguments used in the query.
     * @return The value of the _data column, which is typically a file path.
     */
    private static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {column};

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    private static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    private static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    private static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    // ************************************************************************************************************************************************************************
    // * Encoding and decoding methods
    // ************************************************************************************************************************************************************************

    public static int IMAGE_MAX_WIDTH = 1080;
    public static int IMAGE_MAX_HEIGHT = 768;

    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId, int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, int pixels) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = pixels;

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }

    public static Bitmap getRoundedImage(Bitmap bitmap, int mBorderColor, int mBorderWidth) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);

        int mBitmapWidth = bitmap.getWidth();
        int mBitmapHeight = bitmap.getHeight();

        RectF mBorderRect = new RectF();
        RectF mDrawableRect = new RectF();

        BitmapShader mBitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

        Paint mBitmapPaint = new Paint();
        Paint mBorderPaint = new Paint();

        mBitmapPaint.setAntiAlias(true);
        mBitmapPaint.setShader(mBitmapShader);

        mBorderPaint.setStyle(Paint.Style.STROKE);
        mBorderPaint.setAntiAlias(true);
        mBorderPaint.setColor(mBorderColor);
        mBorderPaint.setStrokeWidth(mBorderWidth);

        mBitmapHeight = bitmap.getHeight();
        mBitmapWidth = bitmap.getWidth();

        mBorderRect.set(0, 0, bitmap.getWidth(), bitmap.getHeight());
        float mBorderRadius = Math.min((mBorderRect.height() - mBorderWidth) / 2, (mBorderRect.width() - mBorderWidth) / 2);

        mDrawableRect.set(mBorderWidth, mBorderWidth, mBorderRect.width() - mBorderWidth, mBorderRect.height() - mBorderWidth);
        float mDrawableRadius = Math.min(mDrawableRect.height() / 2, mDrawableRect.width() / 2);

        Matrix mShaderMatrix = new Matrix();

        float scale;
        float dx = 0;
        float dy = 0;

        mShaderMatrix.set(null);

        if (mBitmapWidth * mDrawableRect.height() > mDrawableRect.width() * mBitmapHeight) {
            scale = mDrawableRect.height() / (float) mBitmapHeight;
            dx = (mDrawableRect.width() - mBitmapWidth * scale) * 0.5f;
        } else {
            scale = mDrawableRect.width() / (float) mBitmapWidth;
            dy = (mDrawableRect.height() - mBitmapHeight * scale) * 0.5f;
        }

        mShaderMatrix.setScale(scale, scale);
        mShaderMatrix.postTranslate((int) (dx + 0.5f) + mBorderWidth, (int) (dy + 0.5f) + mBorderWidth);

        mBitmapShader.setLocalMatrix(mShaderMatrix);

        Canvas canvas = new Canvas(output);

        canvas.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2, mDrawableRadius, mBitmapPaint);
        canvas.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2, mBorderRadius, mBorderPaint);

        return output;
    }

    public static Bitmap decodeSampledBitmapFromFile(String url, int width, int height) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(url, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, width, height);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(url, options);
    }

    private static Bitmap decodeSampledBitmapFromFile(String url, int size) {
        return decodeSampledBitmapFromFile(url, size, size);
    }

    // ************************************************************************************************************************************************************************
    // * Rotate picture methods
    // ************************************************************************************************************************************************************************

    public static Bitmap decodeAndRotateImage(String imagePath, int size) {
        Bitmap bitmap = decodeSampledBitmapFromFile(imagePath, size);
        if (isRotated(imagePath)) {
            int orientation = getOrientation(imagePath);
            bitmap = rotateBitmap(bitmap, orientation);
            Timber.d("imaged has been  rotated ");
        }
        return bitmap;
    }

    public static int getOrientation(String filename) {
        ExifInterface exif;
        int orientation = 0;
        try {
            exif = new ExifInterface(filename);
            orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return orientation;
    }

    public static boolean isRotated(String filename) {
        return getOrientation(filename) > 0;
    }

    public static Bitmap rotateBitmap(Bitmap bitmap, int orientation) {

        Matrix matrix = new Matrix();
        switch (orientation) {
            case ExifInterface.ORIENTATION_NORMAL:
                return bitmap;
            case ExifInterface.ORIENTATION_FLIP_HORIZONTAL:
                matrix.setScale(-1, 1);
                break;
            case ExifInterface.ORIENTATION_ROTATE_180:
                matrix.setRotate(180);
                break;
            case ExifInterface.ORIENTATION_FLIP_VERTICAL:
                matrix.setRotate(180);
                matrix.postScale(-1, 1);
                break;
            case ExifInterface.ORIENTATION_TRANSPOSE:
                matrix.setRotate(90);
                matrix.postScale(-1, 1);
                break;
            case ExifInterface.ORIENTATION_ROTATE_90:
                matrix.setRotate(90);
                break;
            case ExifInterface.ORIENTATION_TRANSVERSE:
                matrix.setRotate(-90);
                matrix.postScale(-1, 1);
                break;
            case ExifInterface.ORIENTATION_ROTATE_270:
                matrix.setRotate(-90);
                break;
            default:
                return bitmap;
        }
        try {
            Timber.d("imaged has been  rotated ");
            Bitmap bmRotated = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            bitmap.recycle();
            return bmRotated;
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            return null;
        }

    }

    public static String getImageEncodedInBase64(String mImageLocalPath, int quality) {

        String encodedImage = null;
        try {
            Bitmap bitmap = decodeSampledBitmapFromFile(mImageLocalPath, IMAGE_MAX_WIDTH, IMAGE_MAX_HEIGHT);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            encodedImage = Base64.encodeToString(byteArray, Base64.DEFAULT);
            byteArray = null;
            bitmap.recycle();
            bitmap = null;
            System.gc();
        } catch (OutOfMemoryError e) {
            Timber.e("OutOfMemoryError: downloading image quality to %d", quality - 10);
            return getImageEncodedInBase64(mImageLocalPath, quality - 10);
        }

        return encodedImage;
    }

    /**
     * Download the image. ALWAYS CALL INSIDE ASYNC TASK
     *
     * @param imageUrl
     * @return
     */
    public static Bitmap getBitmapFromURL(String imageUrl) {
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);

            return myBitmap;

        } catch (IOException e) {
            e.printStackTrace();
            Timber.e("getBmpFromUrl: ", e.getMessage());
            return null;
        }
    }

    public static boolean checkCameraHardware(Context context) {
        return context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA);
    }
}
