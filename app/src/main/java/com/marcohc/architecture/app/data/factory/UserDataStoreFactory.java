package com.marcohc.architecture.app.data.factory;

import com.marcohc.architecture.app.data.cache.UserCache;
import com.marcohc.architecture.app.data.datastore.user.UserDataStore;
import com.marcohc.architecture.app.data.datastore.user.UserDiskDataStore;
import com.marcohc.architecture.app.data.datastore.user.UsersWithPictureDataStore;
import com.marcohc.architecture.app.data.datastore.user.UsersWithoutPictureDataStore;

/**
 * The factory provides different data sources based on variables.
 * <p>
 * It responsibility is to hide the origin of each data source.
 *
 * @author Marco Hernaiz
 * @since 08/08/16
 */
public final class UserDataStoreFactory {

    // ************************************************************************************************************************************************************************
    // * Attributes
    // ************************************************************************************************************************************************************************

    private static UserDataStoreFactory sInstance;
    private UserDataStore mUserWithPicturesDataStore;
    private UserDataStore mUserWithoutPicturesDataStore;
    private UserDataStore mUserDiskDataStore;

    // ************************************************************************************************************************************************************************
    // * Initialization methods
    // ************************************************************************************************************************************************************************

    private UserDataStoreFactory() {
        mUserWithPicturesDataStore = new UsersWithPictureDataStore();
        mUserWithoutPicturesDataStore = new UsersWithoutPictureDataStore();
        mUserDiskDataStore = new UserDiskDataStore();
    }

    /**
     * Get a singleton instance of the class.
     *
     * @return UserDataStoreFactory
     */
    public static UserDataStoreFactory getInstance() {
        if (sInstance == null) {
            sInstance = new UserDataStoreFactory();
        }
        return sInstance;
    }

    // ************************************************************************************************************************************************************************
    // * Factory methods
    // ************************************************************************************************************************************************************************

    /**
     * Get user with pictures data store.
     *
     * @param useCache if you want to use the cache
     * @return the data source
     */
    public UserDataStore getUsersWithPictureDataStore(boolean useCache) {
        String key = UserCache.ALL_WITH_PICTURE;
        if (useCache && UserCache.getInstance().isCached(key) && UserCache.getInstance().isValid(key)) {
            return mUserDiskDataStore;
        } else {
            return mUserWithPicturesDataStore;
        }
    }

    /**
     * Get user without pictures data store.
     *
     * @param useCache if you want to use the cache
     * @return the data source
     */
    public UserDataStore getUsersWithoutPictureDataStore(boolean useCache) {
        String key = UserCache.ALL_WITHOUT_PICTURE;
        if (useCache && UserCache.getInstance().isCached(key) && UserCache.getInstance().isValid(key)) {
            return mUserDiskDataStore;
        } else {
            return mUserWithoutPicturesDataStore;
        }
    }
}
