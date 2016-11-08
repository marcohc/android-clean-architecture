package com.marcohc.architecture.rx.domain.interactor;

import android.support.annotation.NonNull;

import rx.Scheduler;

/**
 * Use case interface.
 *
 * @param <M>
 * @author Marco Hernaiz
 * @since 09/08/16
 */
public interface RxUseCase<M> {

    /**
     * Creates the subscription with the parameters.
     * <p>
     * Override it if you want to do a custom subscription management
     *
     * @param subscriber  the subscriber
     * @param subscribeOn "in which thread you want the use case executed"
     * @param observeOn   "in which thread you want to get the data back"
     */
    void execute(@NonNull SimpleSubscriber<M> subscriber, @NonNull Scheduler subscribeOn, @NonNull Scheduler observeOn);

    /**
     * Cancel the current use case unsubscribing from current {@link rx.Subscription}.
     */
    void cancel();
}
