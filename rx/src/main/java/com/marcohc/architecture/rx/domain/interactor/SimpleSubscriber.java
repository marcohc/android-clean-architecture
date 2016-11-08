package com.marcohc.architecture.rx.domain.interactor;

import rx.Subscriber;

/**
 * An implementation of {@link Subscriber} that has empty method bodies.
 * You may prefer to extend this class if you don't need to override all methods.
 *
 * @param <T> the type of items the Subscriber expects to observe
 * @author Marco Hernaiz
 * @since 09/08/16
 */
public class SimpleSubscriber<T> extends Subscriber<T> {

    @Override
    public void onCompleted() {
        // no-op
    }

    @Override
    public void onError(Throwable e) {
        // no-op
    }

    @Override
    public void onNext(T t) {
        // no-op
    }
}
