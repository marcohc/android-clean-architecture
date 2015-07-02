package com.xs2mobile.buurapp.presentation.presenter.impl;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Base64;
import android.util.Log;

import com.marcohc.helperoid.ImageHelper;
import com.xs2mobile.buurapp.domain.interactor.impl.ProfileDetailInteractorImpl;
import com.xs2mobile.buurapp.domain.interactor.inter.ProfileDetailInteractor;
import com.xs2mobile.buurapp.presentation.model.UserModel;
import com.xs2mobile.buurapp.presentation.presenter.BasePresenter;
import com.xs2mobile.buurapp.presentation.presenter.inter.ProfileDetailPresenter;
import com.xs2mobile.buurapp.presentation.util.FileSystemManager;
import com.xs2mobile.buurapp.presentation.view.inter.ProfileDetailView;

import org.apache.commons.lang3.StringUtils;

import java.io.ByteArrayOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ProfileDetailPresenterImpl extends BasePresenter<ProfileDetailView, ProfileDetailInteractor> implements ProfileDetailPresenter {

    private Uri fileUri;
    private String imagePath;

    @Override
    public ProfileDetailInteractor createInteractor() {
        return new ProfileDetailInteractorImpl(this);
    }

    // ************************************************************************************************************************************************************************
    // * View handler methods
    // ************************************************************************************************************************************************************************

    @Override
    public void onCreateProfile() {
        if (isValidCreationForm()) {
            getView().showLoading(true);
            getInteractor().registerUser();
        }
    }

    @Override
    public void onEditProfile() {
        if (isValidModificationForm()) {
            getView().showLoading(true);
            getInteractor().updateUser(getUser());
        }
    }

    @Override
    public UserModel getUser() {
        return getInteractor().getUser();
    }

    @Override
    public void onAddPhotoImageClick() {
        if (imagePath != null) {
            getView().openImage(imagePath);
        } else {
            fileUri = Uri.fromFile(FileSystemManager.getPhotoFile());
            ImageHelper.showPickUpImageDialog((Activity) getView(), fileUri);
        }
    }

    @Override
    public void onAddPhotoImageActivityResult(int requestCode, Intent intent) {

        try {
            if (requestCode == ImageHelper.CAMERA_REQUEST && fileUri != null) {
                imagePath = fileUri.getPath();
            } else if (requestCode == ImageHelper.SELECT_PICTURE_FROM_GALLERY_REQUEST && intent != null && intent.getData() != null) {
                fileUri = intent.getData();
                imagePath = ImageHelper.getPath((Context) getView(), fileUri);
            }

            Bitmap bitmap = ImageHelper.decodeAndRotateImage(imagePath, 100);
            if (bitmap != null) {
                getView().loadImage(bitmap);
            }
        } catch (Exception e) {
            getView().showError("Exception: " + e.getMessage());
        }
    }

    // ************************************************************************************************************************************************************************
    // * Interactor handler methods
    // ************************************************************************************************************************************************************************

    @Override
    public UserModel getUserFromForm() {
        UserModel user = new UserModel();
        ProfileDetailView view = getView();
        user.setUsername(view.getUsername());
        user.setFirstName(view.getFirsName());
        user.setLastName(view.getLastName());

        Date date;
        String birthday = getView().getBirthday();
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            date = simpleDateFormat.parse(birthday);
            simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            user.setDateOfBirth(simpleDateFormat.format(date));
        } catch (ParseException ex) {
            getView().invalidateBirthday();
        }

        user.setEmail(view.getEmail());
        if (!StringUtils.isBlank(view.getPassword())) {
            user.setPassword(view.getPassword());
        } else {
            user.setPassword(view.getNewPassword());
        }
        user.setImage(imagePath != null ? getImageEncodedInBase64(imagePath, 50) : "");
        user.getAddress().setStreet(view.getStreet());
        user.getAddress().setNumber(view.getNumber());
        user.getAddress().setPostcode(view.getPostcode());
        user.getAddress().setCity(view.getCity());
        user.getAddress().setCountry(view.getCountry());
        return user;
    }

    @Override
    public void goBack() {
        getView().goToMain();
    }

    @Override
    public String getUsername() {
        return getView().getUsername();
    }

    @Override
    public String getPassword() {
        return getView().getPassword();
    }

    // ************************************************************************************************************************************************************************
    // * Presenter auxiliary methods
    // ************************************************************************************************************************************************************************

    private boolean isValidCreationForm() {

        if (!isValidateBasicFields()) {
            return false;
        }

        if (!getView().getPassword().equals(getView().getConfirmPassword())) {
            getView().invalidateCreationPasswordContainer();
            return false;
        }

        return true;
    }

    private boolean isValidModificationForm() {

        if (!isValidateBasicFields()) {
            return false;
        }

        // It means password is not being updated
        if (getView().getCurrentPassword().isEmpty() && getView().getNewPassword().isEmpty() && getView().getConfirmNewPassword().isEmpty()) {
            return true;
        }

        if (!getView().getNewPassword().equals(getView().getConfirmNewPassword()) || !getView().getCurrentPassword().equals(getUser().getPassword())) {
            getView().invalidateModificationPasswordContainer();
            return false;
        }

        return true;
    }

    private boolean isValidateBasicFields() {

        if (StringUtils.isBlank(getView().getUsername())) {
            getView().invalidateUsername();
            return false;
        }

        if (StringUtils.isBlank(getView().getFirsName())) {
            getView().invalidateFirstName();
            return false;
        }

        if (StringUtils.isBlank(getView().getLastName())) {
            getView().invalidateLastName();
            return false;
        }

        if (StringUtils.isBlank(getView().getStreet())) {
            getView().invalidateStreet();
            return false;
        }

        if (StringUtils.isBlank(getView().getNumber())) {
            getView().invalidateNumber();
            return false;
        }

        if (StringUtils.isBlank(getView().getPostcode())) {
            getView().invalidatePostCode();
            return false;
        }

        if (StringUtils.isBlank(getView().getCity())) {
            getView().invalidateCity();
            return false;
        }

        if (StringUtils.isBlank(getView().getBirthday())) {
            getView().invalidateBirthday();
            return false;
        }

        Date date;
        String birthday = getView().getBirthday();
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            date = simpleDateFormat.parse(birthday);
            if (!birthday.equals(simpleDateFormat.format(date))) {
                return false;
            }
        } catch (ParseException ex) {
            getView().invalidateBirthday();
            return false;
        }

        if (StringUtils.isBlank(getView().getEmail())) {
            getView().invalidateEmail();
            return false;
        }
        return true;
    }

    private String getImageEncodedInBase64(String mImageLocalPath, int quality) {
        String encodedImage;
        try {
            Bitmap e = decodeAndRotateImage(mImageLocalPath, 1024, 768);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            e.compress(Bitmap.CompressFormat.JPEG, quality, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            encodedImage = Base64.encodeToString(byteArray, 0);
            Object byteArray1 = null;
            e.recycle();
            e = null;
            System.gc();
            return encodedImage;
        } catch (OutOfMemoryError var6) {
            Log.e("ImageHelper", String.format("OutOfMemoryError: downloading image quality to %d", new Object[]{Integer.valueOf(quality - 10)}));
            return getImageEncodedInBase64(mImageLocalPath, quality - 10);
        }
    }

    private Bitmap decodeAndRotateImage(String imagePath, int width, int height) {
        Bitmap bitmap = decodeSampledBitmapFromFile(imagePath, width, height);
        if (ImageHelper.isRotated(imagePath)) {
            int orientation = ImageHelper.getOrientation(imagePath);
            bitmap = ImageHelper.rotateBitmap(bitmap, orientation);
            Log.d("ImageHelper", "imaged has been  rotated ");
        }

        return bitmap;
    }

    public static Bitmap decodeSampledBitmapFromFile(String url, int width, int height) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(url, options);
        options.inSampleSize = ImageHelper.calculateInSampleSize(options, width, height);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(url, options);
    }

}
