package com.marcohc.architecture.rx.presentation.mvp;

import android.support.annotation.NonNull;
import android.support.annotation.UiThread;

import com.marcohc.architecture.presentation.presenter.BaseMvpPresenter;
import com.marcohc.architecture.presentation.view.BaseMvpView;
import com.marcohc.architecture.rx.domain.executor.RxJobExecutor;
import com.marcohc.architecture.rx.domain.interactor.BaseSubscriber;
import com.marcohc.architecture.rx.domain.interactor.RxUseCase;

import timber.log.Timber;

public abstract class BaseRxMvpPresenter<V extends BaseMvpView> extends BaseMvpPresenter<V> {

    /**
     * Executes a use case using the RxJobExecutor.
     * <p>
     * It'll be executed in the pool thread and returned in the MainThread
     *
     * @param useCase    use case to execute
     * @param subscriber the subscriber for Rx
     */
    @UiThread
    protected void executeUseCase(@NonNull RxUseCase useCase, @NonNull BaseSubscriber subscriber) {
        Timber.d("executeUseCase: %s", useCase.getClass().getSimpleName());
        RxJobExecutor.getInstance().executeUseCase(useCase, subscriber);
    }

}
