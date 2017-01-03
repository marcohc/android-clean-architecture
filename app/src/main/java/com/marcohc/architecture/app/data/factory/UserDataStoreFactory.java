package com.marcohc.architecture.app.data.factory;

import com.marcohc.architecture.app.data.datastore.user.UserCacheDataStore;
import com.marcohc.architecture.app.data.datastore.user.UserCloudDataStore;
import com.marcohc.architecture.app.data.datastore.user.UserDiskDataStore;
import com.marcohc.architecture.app.data.datastore.user.UsersWithPictureDataStore;
import com.marcohc.architecture.app.data.datastore.user.UsersWithoutPictureDataStore;

/**
 * The factory provides different data sources .
 * <p>
 * Its responsibility is to hide the origin of each data source.
 *
 * @author Marco Hernaiz
 * @since 08/08/16
 */
public final class UserDataStoreFactory {

    public static UserDiskDataStore createUserDiskDataStore() {
        return new UserCacheDataStore();
    }

    public static UserCloudDataStore createUserWithoutPicturesCloudDataStore() {
        return new UsersWithoutPictureDataStore();
    }

    public static UserCloudDataStore createUserWithPicturesCloudDataStore() {
        return new UsersWithPictureDataStore();
    }

}
