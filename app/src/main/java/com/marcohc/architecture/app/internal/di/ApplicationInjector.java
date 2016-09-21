package com.marcohc.architecture.app.internal.di;

import android.content.Context;

/**
 * Utility class for dependency injection.
 *
 * @author Marco Hernaiz
 * @since 08/08/16
 */
public final class ApplicationInjector {

    private static ApplicationComponent sApplicationComponent;

    private ApplicationInjector() {
    }

    /**
     * Inject the context.
     *
     * @param context
     */
    public static void inject(Context context) {
        sApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(context))
                .build();
    }

    public static ApplicationComponent getApplicationComponent() {
        return sApplicationComponent;
    }
}
