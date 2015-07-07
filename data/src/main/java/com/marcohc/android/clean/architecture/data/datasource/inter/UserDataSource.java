package com.marcohc.android.clean.architecture.data.datasource.inter;

import com.marcohc.android.clean.architecture.data.net.RepositoryCallback;

public interface UserDataSource {

    void logIn(String username, String password, RepositoryCallback callback);

}
