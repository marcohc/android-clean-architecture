package com.marcohc.architecture.app.domain.model;

import com.google.gson.annotations.SerializedName;
import com.marcohc.architecture.domain.model.Model;

/**
 * User model.
 *
 * @author Marco Hernaiz
 * @since 08/08/16
 */
public class UserModel implements Model {

    // ************************************************************************************************************************************************************************
    // * Attributes
    // ************************************************************************************************************************************************************************

    @SerializedName("name")
    private String mName;

    @SerializedName("email")
    private String mEmail;

    @SerializedName("pictureUrl")
    private String mPictureUrl;

    // ************************************************************************************************************************************************************************
    // * Constructors
    // ************************************************************************************************************************************************************************

    public UserModel() {
        mName = "Unknown";
        mEmail = "Unknown";
    }

    // ************************************************************************************************************************************************************************
    // * Getter and setters
    // ************************************************************************************************************************************************************************

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        this.mEmail = email;
    }

    public String getPictureUrl() {
        return mPictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.mPictureUrl = pictureUrl;
    }
}
