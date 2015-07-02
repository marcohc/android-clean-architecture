package com.xs2mobile.buurapp.presentation.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.xs2mobile.buurapp.presentation.util.Constants;
import com.xs2mobile.buurapp.presentation.view.impl.adapter.viewholder.NeighbourViewHolder;
import com.xs2mobile.buurapp.presentation.view.impl.adapter.viewholder.ViewHolderAbstractClass;

import org.apache.commons.lang3.StringUtils;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class UserModel extends BaseModel {

    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String dateOfBirth;
    private String image;
    private String token;
    private AddressModel address;

    public UserModel() {
        address = new AddressModel();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonProperty("first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @JsonProperty("last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @JsonProperty("date_of_birth")
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AddressModel getAddress() {
        return address;
    }

    public void setAddress(AddressModel address) {
        this.address = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public ViewHolderAbstractClass getDefaultViewHolder(Constants.DATA_TYPE itemType) {

        if (itemType.equals(Constants.DATA_TYPE.NEIGHBOURS)) {
            return new NeighbourViewHolder();
        } else {
            return null;
        }
    }

    public String getFullName() {
        String fullName = "";
        fullName += StringUtils.isBlank(firstName) ? "" : firstName;
        fullName += " ";
        fullName += StringUtils.isBlank(lastName) ? "" : lastName;
        return fullName;
    }
}
