package com.marcohc.architecture.presentation.bus.presentation.mvp;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.marcohc.architecture.presentation.bus.common.BusProvider;
import com.marcohc.architecture.presentation.view.BaseMvpView;
import com.marcohc.architecture.presentation.view.activity.BaseMvpActivity;

/**
 * Base bus activity which automatically register into the event bus.
 * <p>
 * Override it for specific common methods in activities.
 *
 * @param <V> the BaseMvpView type the superclass is implementing
 * @param <P> the type of MvpPresenter which will handle the logic of the class
 * @author Marco Hernaiz
 * @since 08/08/16
 */
public abstract class BaseBusMvpActivity<V extends BaseMvpView, P extends MvpPresenter<V>> extends BaseMvpActivity<V, P> implements BaseMvpView {

    @Override
    public void onStart() {
        super.onStart();
        BusProvider.register(presenter);
    }

    @Override
    public void onStop() {
        BusProvider.unregister(presenter);
        super.onStop();
    }

}
