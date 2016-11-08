package com.marcohc.architecture.rx.domain.interactor;

import android.support.annotation.NonNull;

import com.marcohc.architecture.common.utils.Preconditions;
import com.marcohc.architecture.rx.domain.executor.RxJobExecutor;

import rx.Observable;
import rx.Scheduler;
import rx.Subscription;
import rx.subscriptions.Subscriptions;

/**
 * Base use case which encapsulates the implementation of Rx.
 * <p>
 * The specific use case "should" be implemented with pure java
 *
 * @param <M>: the model response you expect to receive in the presentation layer
 */
public abstract class BaseRxUseCase<M> implements RxUseCase<M> {

    @NonNull
    private Subscription mSubscription = Subscriptions.empty();

    /**
     * Get the observable from where you want to get the data.
     * <p>
     *
     * @return the observable
     */
    @NonNull
    protected abstract Observable<M> getObservable();

    /**
     * Execute the use case. This method is called through {@link RxJobExecutor},
     * but you can override it handle Rx as you like.
     *
     * @param subscriber the subscriber
     * @param subscribeOn "in which thread you want the use case executed"
     * @param observeOn "in which thread you want to get the data back"
     */
    @SuppressWarnings("unchecked")
    @Override
    public void execute(@NonNull final SimpleSubscriber<M> subscriber, @NonNull Scheduler subscribeOn, @NonNull Scheduler observeOn) {
        Preconditions.checkNotNull(subscriber, "subscriber");
        Preconditions.checkNotNull(subscribeOn, "subscribeOn");
        Preconditions.checkNotNull(observeOn, "observeOn");

        this.mSubscription = getObservable()
                .subscribeOn(subscribeOn)
                .observeOn(observeOn)
                .subscribe(subscriber);
    }

    /**
     * Cancel the mSubscription of the use case.
     */
    @Override
    public void cancel() {
        if (!mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }
}
