package com.marcohc.android.clean.architecture.data.repository.datastore.datastores.disk;

import com.marcohc.android.clean.architecture.data.repository.datastore.UserDataStore;
import com.marcohc.android.clean.architecture.data.net.RestCallback;
import com.marcohc.android.clean.architecture.domain.entity.UserEntity;

public class UserDiskDataSource implements UserDataStore {

    // ************************************************************************************************************************************************************************
    // * User methods
    // ************************************************************************************************************************************************************************

    @Override
    public void logIn(String username, String password, RestCallback callback) {
        throw new UnsupportedOperationException("Operation is not available!!!");
    }

    @Override
    public void signUp(String username, String password, RestCallback callback) {
        throw new UnsupportedOperationException("Operation is not available!!!");
    }

    @Override
    public void get(RestCallback callback) {
        throw new UnsupportedOperationException("Operation is not available!!!");
    }

    @Override
    public void getAll(RestCallback callback) {
        throw new UnsupportedOperationException("Operation is not available!!!");
    }

    @Override
    public void delete() {
        throw new UnsupportedOperationException("Operation is not available!!!");
//        PreferencesHelper.remove(PreferencesConstants.USER);
    }

    @Override
    public void put(UserEntity entity) {
        throw new UnsupportedOperationException("Operation is not available!!!");
//        PreferencesHelper.putString(PreferencesConstants.USER, entity.toJsonString());
    }


//    @Override
//    public UserEntity get() {
//        return ParserHelper.parse(PreferencesHelper.getString(PreferencesConstants.USER, ""), UserEntity.class);
//    }
//
//    @Override
//    public List<UserEntity> getAll() {
//        throw new UnsupportedOperationException("Operation is not available!!!");
//    }


}
