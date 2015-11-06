package com.marcohc.android.clean.architecture.data.repository.datastore.disk;

import com.marcohc.android.clean.architecture.data.repository.datastore.UserDataStore;
import com.marcohc.android.clean.architecture.data.repository.net.RepositoryCallback;
import com.marcohc.android.clean.architecture.domain.entity.UserEntity;
import com.marcohc.helperoid.ParserHelper;
import com.marcohc.helperoid.PreferencesHelper;

import java.util.List;

public class UserDiskDataSource implements UserDataStore, DiskRestDataSource<UserEntity> {

    // ************************************************************************************************************************************************************************
    // * Constants
    // ************************************************************************************************************************************************************************

    private static final String USER = "user";

    // ************************************************************************************************************************************************************************
    // * User methods
    // ************************************************************************************************************************************************************************

    @Override
    public UserEntity get() {
        return ParserHelper.parse(PreferencesHelper.getString(USER, ""), UserEntity.class);
    }

    @Override
    public List<UserEntity> getAll() {
        throw new UnsupportedOperationException("Operation is not available!!!");
    }

    @Override
    public void logIn(String username, String password, RepositoryCallback callback) {
        throw new UnsupportedOperationException("Operation is not available!!!");
    }

    @Override
    public boolean isFirstTimeInApp() {
        return PreferencesHelper.isFirstAppInstallation();
    }

    @Override
    public void delete() {
        PreferencesHelper.remove(USER);
    }

    @Override
    public void put(UserEntity entity) {
        PreferencesHelper.putString(USER, entity.toJsonString());
    }
}
