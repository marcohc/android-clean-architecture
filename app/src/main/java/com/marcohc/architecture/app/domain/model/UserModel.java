package com.marcohc.architecture.app.domain.model;

import com.marcohc.architecture.domain.model.BaseJsonModel;
import com.marcohc.architecture.domain.model.Model;

public class UserModel extends BaseJsonModel implements Model {

    // ************************************************************************************************************************************************************************
    // * Attributes
    // ************************************************************************************************************************************************************************

    private String name;
    private String email;
    private String pictureUrl;

    // ************************************************************************************************************************************************************************
    // * Constructors
    // ************************************************************************************************************************************************************************

    public UserModel() {
        name = "Unknown";
        email = "Unknown";
    }

    // ************************************************************************************************************************************************************************
    // * Getter and setters
    // ************************************************************************************************************************************************************************

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }
}
