package com.marcohc.architecture.domain.interactor;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Callback used by Presenter and UseCase which hides Rx implementation.
 *
 * @param <T> the model type returned
 * @author Marco Hernaiz
 * @since 09/08/16
 */
public abstract class BaseSubscriber<T> {

    protected abstract void onSuccess(@Nullable T domainResponse);

    protected abstract void onFailure(@NonNull Throwable throwable);

}
