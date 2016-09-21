package com.marcohc.architecture.app.internal.di;

import android.content.Context;

import com.marcohc.architecture.app.data.repository.user.UserRepository;
import com.marcohc.architecture.app.data.repository.user.UserRepositoryImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module.
 *
 * @author Marco Hernaiz
 * @since 08/08/16
 */
@Module
public class ApplicationModule {

    private final Context mContext;

    ApplicationModule(Context context) {
        this.mContext = context;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return mContext;
    }

    @Provides
    @Singleton
    UserRepository provideUserRepository() {
        return new UserRepositoryImpl();
    }
}
