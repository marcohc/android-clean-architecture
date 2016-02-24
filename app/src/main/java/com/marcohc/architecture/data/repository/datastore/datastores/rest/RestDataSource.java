package com.marcohc.architecture.data.repository.datastore.datastores.rest;

import com.marcohc.architecture.data.net.RestCallback;

/**
 * REST data source methods
 */
public interface RestDataSource<T> {

    void delete();

    void put(T entity);

    void get(RestCallback callback);

    void getAll(RestCallback callback);
}
