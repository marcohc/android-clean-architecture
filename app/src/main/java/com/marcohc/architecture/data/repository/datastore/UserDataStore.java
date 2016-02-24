package com.marcohc.architecture.data.repository.datastore;

import com.marcohc.architecture.data.repository.datastore.datastores.rest.RestDataSource;
import com.marcohc.architecture.data.net.RestCallback;
import com.marcohc.architecture.domain.entity.UserEntity;

/**
 * Specific methods for this model go here. REST methods are excluded
 */
public interface UserDataStore extends RestDataSource<UserEntity> {

    void logIn(String username, String password, RestCallback callback);

    void signUp(String username, String password, RestCallback callback);

}
