package com.marcohc.android.clean.architecture.data.repository.datastore.datastores.rest;

import com.marcohc.android.clean.architecture.data.repository.datastore.UserDataStore;
import com.marcohc.android.clean.architecture.data.repository.datastore.datastores.rest.util.ServiceGenerator;
import com.marcohc.android.clean.architecture.data.net.RestCallback;
import com.marcohc.android.clean.architecture.data.util.NetworkManager;
import com.marcohc.android.clean.architecture.domain.entity.UserEntity;

import org.json.JSONException;
import org.json.JSONObject;

public class UserRestDataStore implements UserDataStore, RestDataSource<UserEntity> {

    @Override
    public void logIn(String username, String password, RestCallback callback) {
        // Fake response
        try {
            callback.success(new JSONObject("{\r\n  \"id\": 47,\r\n  \"last_login\": \"2015-07-07T14:31:23.133Z\",\r\n  \"is_superuser\": false,\r\n  \"username\": \"SuperUserName\",\r\n  \"first_name\": \"MyFirstName\",\r\n  \"last_name\": \"MyLastName\",\r\n  \"email\": \"my_ultra_fake_email_address@gmail.com\",\r\n  \"is_staff\": false,\r\n  \"is_active\": true,\r\n  \"date_joined\": \"2015-07-07T14:31:23.133Z\",\r\n  \"image\": \"\",\r\n  \"updated\": \"2015-07-07T14:31:23.342Z\",\r\n  \"date_of_birth\": \"1950-12-29\",\r\n  \"token\": \"21b18bca6eefd776227579c93b446ea637cc1384\",\r\n  \"user_id\": 47\r\n}"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void signUp(String username, String password, RestCallback callback) {
        try {
            callback.success(new JSONObject("{\r\n  \"id\": 47,\r\n  \"last_login\": \"2015-07-07T14:31:23.133Z\",\r\n  \"is_superuser\": false,\r\n  \"username\": \"SuperUserName\",\r\n  \"first_name\": \"MyFirstName\",\r\n  \"last_name\": \"MyLastName\",\r\n  \"email\": \"my_ultra_fake_email_address@gmail.com\",\r\n  \"is_staff\": false,\r\n  \"is_active\": true,\r\n  \"date_joined\": \"2015-07-07T14:31:23.133Z\",\r\n  \"image\": \"\",\r\n  \"updated\": \"2015-07-07T14:31:23.342Z\",\r\n  \"date_of_birth\": \"1950-12-29\",\r\n  \"token\": \"21b18bca6eefd776227579c93b446ea637cc1384\",\r\n  \"user_id\": 47\r\n}"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getAll(RestCallback callback) {
        UserRestService userRestService = ServiceGenerator.createService(UserRestService.class, NetworkManager.BASE_API_URL);
        userRestService.getAll(callback);
    }

    @Override
    public void delete() {
        throw new UnsupportedOperationException("Operation is not available!!!");
    }

    @Override
    public void put(UserEntity entity) {
        throw new UnsupportedOperationException("Operation is not available!!!");
    }

    @Override
    public void get(RestCallback callback) {
        throw new UnsupportedOperationException("Operation is not available!!!");
    }


}
