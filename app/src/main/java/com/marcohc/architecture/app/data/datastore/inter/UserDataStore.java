package com.marcohc.architecture.app.data.datastore.inter;

import com.marcohc.architecture.app.domain.model.UserModel;
import com.marcohc.architecture.data.net.DataCallback;

import java.util.List;

/**
 * Specific methods for this model go here
 */
public interface UserDataStore {

    void getAll(DataCallback<List<UserModel>> callback);
}
