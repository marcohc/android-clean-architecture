package com.marcohc.android.clean.architecture.data.repository.datastore.disk;

import java.util.List;

/**
 * TODO: Standard REST methods
 */
public interface DiskRestDataSource<T> {

    void delete();

    void put(T entity);

    T get();

    List<T> getAll();
}
