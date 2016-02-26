
package com.marcohc.architecture.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserModel extends BaseModel {

    private String key;
    private String gender;
    private Name name;
    private Localization location;
    private String email;
    private String username;
    private String password;
    private String salt;
    private String md5;
    private String sha1;
    private String sha256;
    private String registered;
    private String dob;
    private String phone;
    private String cell;
    private String tfn;
    private Picture picture;
    private String version;

    /**
     * @return The gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender The gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return The name
     */
    public Name getName() {
        return name;
    }

    /**
     * @param name The name
     */
    public void setName(Name name) {
        this.name = name;
    }

    /**
     * @return The location
     */
    public Localization getLocation() {
        return location;
    }

    /**
     * @param location The location
     */
    public void setLocation(Localization location) {
        this.location = location;
    }

    /**
     * @return The email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email The email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return The username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username The username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return The password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password The password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return The salt
     */
    public String getSalt() {
        return salt;
    }

    /**
     * @param salt The salt
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     * @return The md5
     */
    public String getMd5() {
        return md5;
    }

    /**
     * @param md5 The md5
     */
    public void setMd5(String md5) {
        this.md5 = md5;
    }

    /**
     * @return The sha1
     */
    public String getSha1() {
        return sha1;
    }

    /**
     * @param sha1 The sha1
     */
    public void setSha1(String sha1) {
        this.sha1 = sha1;
    }

    /**
     * @return The sha256
     */
    public String getSha256() {
        return sha256;
    }

    /**
     * @param sha256 The sha256
     */
    public void setSha256(String sha256) {
        this.sha256 = sha256;
    }

    /**
     * @return The registered
     */
    public String getRegistered() {
        return registered;
    }

    /**
     * @param registered The registered
     */
    public void setRegistered(String registered) {
        this.registered = registered;
    }

    /**
     * @return The dob
     */
    public String getDob() {
        return dob;
    }

    /**
     * @param dob The dob
     */
    public void setDob(String dob) {
        this.dob = dob;
    }

    /**
     * @return The phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone The phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return The cell
     */
    public String getCell() {
        return cell;
    }

    /**
     * @param cell The cell
     */
    public void setCell(String cell) {
        this.cell = cell;
    }

    /**
     * @return The tfn
     */
    public String getTfn() {
        return tfn;
    }

    /**
     * @param tfn The tfn
     */
    public void setTfn(String tfn) {
        this.tfn = tfn;
    }

    /**
     * @return The picture
     */
    public Picture getPicture() {
        return picture;
    }

    /**
     * @param picture The picture
     */
    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    /**
     * @return The version
     */
    public String getVersion() {
        return version;
    }

    /**
     * @param version The version
     */
    public void setVersion(String version) {
        this.version = version;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
