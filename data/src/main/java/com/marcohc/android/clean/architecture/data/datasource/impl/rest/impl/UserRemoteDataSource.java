package com.marcohc.android.clean.architecture.data.datasource.impl.rest.impl;


import com.marcohc.android.clean.architecture.common.exception.DataException;
import com.marcohc.android.clean.architecture.data.datasource.impl.rest.ServiceGenerator;
import com.marcohc.android.clean.architecture.data.datasource.impl.rest.inter.UserApiService;
import com.marcohc.android.clean.architecture.data.datasource.inter.UserDataSource;
import com.marcohc.android.clean.architecture.data.net.RepositoryCallback;
import com.marcohc.android.clean.architecture.data.util.NetworkManager;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class UserRemoteDataSource implements UserDataSource {

    @Override
    public void logIn(String username, String password, RepositoryCallback callback) {
//        UserApiService userApiService = ServiceGenerator.createService(UserApiService.class, NetworkManager.BASE_API_URL, username, password);
//        userApiService.logIn(callback);

        // Fake response
        if (!StringUtils.isBlank(username) && !StringUtils.isBlank(password) && username.equals("admin") && password.equals("admin")) {
            try {
                callback.success(new JSONObject("{\r\n  \"id\": 47,\r\n  \"last_login\": \"2015-07-07T14:31:23.133Z\",\r\n  \"is_superuser\": false,\r\n  \"username\": \"SuperUserName\",\r\n  \"first_name\": \"MyFirstName\",\r\n  \"last_name\": \"MyLastName\",\r\n  \"email\": \"my_ultra_fake_email_address@gmail.com\",\r\n  \"is_staff\": false,\r\n  \"is_active\": true,\r\n  \"date_joined\": \"2015-07-07T14:31:23.133Z\",\r\n  \"image\": \"\",\r\n  \"updated\": \"2015-07-07T14:31:23.342Z\",\r\n  \"date_of_birth\": \"1950-12-29\",\r\n  \"token\": \"21b18bca6eefd776227579c93b446ea637cc1384\",\r\n  \"user_id\": 47\r\n}"));
            } catch (JSONException ignored) {
            }
        } else {
            callback.failure(new DataException("User or password incorrect", -1));
        }
    }

    @Override
    public void get(RepositoryCallback callback) {
        UserApiService userApiService = ServiceGenerator.createService(UserApiService.class, NetworkManager.BASE_API_URL);
        userApiService.get(callback);
    }
}
