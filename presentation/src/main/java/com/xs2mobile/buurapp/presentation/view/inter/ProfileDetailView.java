package com.xs2mobile.buurapp.presentation.view.inter;

import android.graphics.Bitmap;

public interface ProfileDetailView extends BaseView {

    String getUsername();

    String getFirsName();

    String getLastName();

    String getStreet();

    String getNumber();

    String getPostcode();

    String getCity();

    String getCountry();

    String getBirthday();

    String getEmail();

    String getPassword();

    String getConfirmPassword();

    String getConfirmNewPassword();

    String getCurrentPassword();

    String getNewPassword();

    void goToMain();

    void loadImage(Bitmap bitmap);

    void openImage(String path);

    void invalidateCreationPasswordContainer();

    void invalidateModificationPasswordContainer();

    void invalidateUsername();

    void invalidateStreet();

    void invalidateNumber();

    void invalidatePostCode();

    void invalidateCity();

    void invalidateEmail();

    void invalidateFirstName();

    void invalidateLastName();

    void invalidateBirthday();
}
