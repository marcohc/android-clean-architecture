package com.marcohc.architecture.domain.interactor;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.marcohc.architecture.common.utils.Preconditions;
import com.marcohc.architecture.data.exception.NotHandledDataException;
import com.marcohc.architecture.data.executor.JobExecutor;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.subscriptions.Subscriptions;

/**
 * Base use case which encapsulates the implementation of Rx.
 * <p>
 * The specific use case "should" be implemented with pure java
 *
 * @param <M>: the model response you expect to receive in the presentation layer
 * @param <E>: the entity response you expect from the data layer
 */
public abstract class BaseUseCase<M, E> implements UseCase<M> {

    @NonNull
    private Subscription mSubscription = Subscriptions.empty();

    /**
     * Get the observable from where you want to get the data.
     * <p>
     *
     * @return the observable
     */
    @NonNull
    protected abstract Observable<E> getObservable();

    /**
     * Here you must transform the data from entities to models.
     * <p>
     *
     * @param dataResponse the data obtained from the data layer
     */
    @Nullable
    protected abstract M transformData(@NonNull E dataResponse);

    /**
     * This is the place to execute the business logic of your use case.
     */
    protected abstract void doBusinessLogic(@Nullable M domainResponse);

    /**
     * Do the exception logic handling here.
     * <p>
     * You must transform from data exception to domain exception and then throw it,
     * otherwise you'll get on the presentation layer a {@link NotHandledDataException}
     *
     * @param throwable the exception from the data layer
     */
    protected abstract void onDataException(@NonNull Throwable throwable) throws Exception;

    /**
     * Execute the use case. This method is called through {@link JobExecutor},
     * but you can override it handle Rx as you like.
     *
     * @param subscriber  the subscriber
     * @param subscribeOn "in which thread you want the use case executed"
     * @param observeOn   "in which thread you want to get the data back"
     */
    @SuppressWarnings("unchecked")
    @Override
    public void execute(@NonNull final BaseSubscriber<M> subscriber, @NonNull Scheduler subscribeOn, @NonNull Scheduler observeOn) {
        Preconditions.checkNotNull(subscriber, "subscriber");
        Preconditions.checkNotNull(subscribeOn, "subscribeOn");
        Preconditions.checkNotNull(observeOn, "observeOn");

        this.mSubscription = getObservable()
                .map(new Func1<E, M>() {
                    @Override
                    public M call(E entity) {
                        return transformData(entity);
                    }
                })
                .doOnNext(new Action1<M>() {
                    @Override
                    public void call(M model) {
                        doBusinessLogic(model);
                    }
                })
                .subscribeOn(subscribeOn)
                .observeOn(observeOn)
                .subscribe(new Subscriber<M>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable dataThrowable) {
                        try {
                            onDataException(dataThrowable);
                        } catch (Throwable domainThrowable) {
                            subscriber.onFailure(domainThrowable);
                            return;
                        }
                        subscriber.onFailure(new NotHandledDataException(dataThrowable));
                    }

                    @Override
                    public void onNext(M model) {
                        subscriber.onSuccess(model);
                    }
                });
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
