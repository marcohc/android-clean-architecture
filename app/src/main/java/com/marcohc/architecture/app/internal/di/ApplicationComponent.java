package com.marcohc.architecture.app.internal.di;

import android.content.Context;

import com.marcohc.architecture.app.data.cache.UserCache;
import com.marcohc.architecture.app.data.repository.user.UserRepository;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Dagger component.
 *
 * @author Marco Hernaiz
 * @since 08/08/16
 */
@Singleton
@PerApplication
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    /**
     * Injects the cache.
     *
     * @param cache
     */
    void inject(UserCache cache);

//    void inject(GetUsersUseCase useCase);

    /**
     * Provides the context.
     * @return Context
     */
    Context provideContext();

    /**
     * Provides the repository.
     * @return UserRepository
     */
    UserRepository provideRepository();

}
