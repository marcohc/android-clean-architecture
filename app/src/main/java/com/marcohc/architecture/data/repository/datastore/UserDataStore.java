package com.marcohc.architecture.data.repository.datastore;

import com.marcohc.architecture.data.net.RestCallback;

/**
 * Specific methods for this model go here
 */
public interface UserDataStore {

    void getAll(RestCallback callback);
}
