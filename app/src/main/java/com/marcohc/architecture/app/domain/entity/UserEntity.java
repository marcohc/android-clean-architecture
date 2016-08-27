package com.marcohc.architecture.app.domain.entity;

import com.marcohc.architecture.domain.entity.Entity;
import com.marcohc.architecture.domain.model.BaseJsonModel;

public class UserEntity extends BaseJsonModel implements Entity {

    // ************************************************************************************************************************************************************************
    // * Attributes
    // ************************************************************************************************************************************************************************

    private String name;
    private String email;
    private String pictureUrl;

    // ************************************************************************************************************************************************************************
    // * Constructors
    // ************************************************************************************************************************************************************************

    public UserEntity() {
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