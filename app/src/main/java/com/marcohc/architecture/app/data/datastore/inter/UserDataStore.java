package com.marcohc.architecture.app.data.datastore.inter;

import com.marcohc.architecture.data.net.RestCallback;

/**
 * Specific methods for this model go here
 */
public interface UserDataStore {

    void getAll(RestCallback callback);
}
