package com.marcohc.android.clean.architecture.data.repository.datastore.cloud;

import com.marcohc.android.clean.architecture.data.repository.net.RepositoryCallback;

/**
 * TODO: Standard REST methods
 */
public interface CloudRestDataSource<T> {

    void delete();

    void put(T entity);

    void get(RepositoryCallback callback);

    void getAll(RepositoryCallback callback);
}
