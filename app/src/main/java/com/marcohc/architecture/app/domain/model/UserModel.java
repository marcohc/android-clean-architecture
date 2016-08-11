package com.marcohc.architecture.app.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.marcohc.architecture.domain.model.BaseJsonModel;
import com.marcohc.architecture.domain.model.Model;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
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
    // * Business logic
    // ************************************************************************************************************************************************************************

    @JsonIgnore
    public String getEmailDomain() {
        String emailDomain = "";
        if (email != null && email.contains("@")) {
            emailDomain = email.substring(email.indexOf("@"), email.length());
        }
        return emailDomain;
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
