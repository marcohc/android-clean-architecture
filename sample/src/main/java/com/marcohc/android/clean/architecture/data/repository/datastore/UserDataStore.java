package com.marcohc.android.clean.architecture.data.repository.datastore;

import com.marcohc.android.clean.architecture.data.repository.net.RepositoryCallback;

/**
 * TODO: Specific methods for this model goes here. REST methods are excluded
 */
public interface UserDataStore {

    void logIn(String username, String password, RepositoryCallback callback);

    void signUp(String username, String password, RepositoryCallback callback);

    boolean isFirstTimeInApp();
}
