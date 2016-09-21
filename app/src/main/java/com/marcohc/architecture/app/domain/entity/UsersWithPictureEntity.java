package com.marcohc.architecture.app.domain.entity;

import com.google.gson.annotations.SerializedName;
import com.marcohc.architecture.domain.entity.Entity;

import java.util.List;

/**
 * User with picture entity.
 *
 * @author Marco Hernaiz
 * @since 08/08/16
 */
public class UsersWithPictureEntity implements Entity {

    // ************************************************************************************************************************************************************************
    // * Attributes
    // ************************************************************************************************************************************************************************

    @SerializedName("results")
    private List<UserWithPicture> mUserWithPicturesList;

    // ************************************************************************************************************************************************************************
    // * Constructors
    // ************************************************************************************************************************************************************************

    public UsersWithPictureEntity() {
    }

    // ************************************************************************************************************************************************************************
    // * Getter and setters
    // ************************************************************************************************************************************************************************

    public List<UserWithPicture> getUserWithPicturesList() {
        return mUserWithPicturesList;
    }

    public void setUserWithPicturesList(List<UserWithPicture> userWithPicturesList) {
        this.mUserWithPicturesList = userWithPicturesList;
    }

    /**
     * This is just an example. Gson needs the inner classes as static
     */
    public static class UserWithPicture {

        @SerializedName("name")
        private Name mName;

        @SerializedName("email")
        private String mEmail;

        @SerializedName("picture")
        private Picture mPicture;

        public UserWithPicture() {

        }

        public Picture getPicture() {
            return mPicture;
        }

        public void setPicture(Picture picture) {
            this.mPicture = picture;
        }

        public String getEmail() {
            return mEmail;
        }

        public void setEmail(String email) {
            this.mEmail = email;
        }

        public Name getName() {
            return mName;
        }

        public void setName(Name name) {
            this.mName = name;
        }

        /**
         * Pojo class.
         */
        public static class Name {

            @SerializedName("first")
            private String mFirst;

            public Name() {
            }

            public String getFirst() {
                return mFirst;
            }

            public void setFirst(String first) {
                this.mFirst = first;
            }
        }

        /**
         * Pojo class.
         */
        public static class Picture {

            @SerializedName("medium")
            private String mUrl;

            public Picture() {
            }

            public String getUrl() {
                return mUrl;
            }

            public void setUrl(String url) {
                this.mUrl = url;
            }
        }
    }
}
