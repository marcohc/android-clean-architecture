package com.marcohc.architecture.rx.presentation.mvp;

import android.support.annotation.NonNull;
import android.support.annotation.UiThread;

import com.marcohc.architecture.aca.presentation.mvp.BaseMvpPresenter;
import com.marcohc.architecture.aca.presentation.mvp.BaseMvpView;
import com.marcohc.architecture.common.thread.JobExecutor;
import com.marcohc.architecture.common.util.utils.Preconditions;
import com.marcohc.architecture.rx.domain.interactor.RxUseCase;
import com.marcohc.architecture.rx.domain.interactor.SimpleSubscriber;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

public abstract class BaseRxMvpPresenter<V extends BaseMvpView> extends BaseMvpPresenter<V> {

    /**
     * Executes a use case using the RxJobExecutor.
     * <p>
     * It'll be executed in the pool thread and returned in the MainThread
     *
     * @param useCase use case to execute
     * @param subscriber the subscriber for Rx
     */
    @UiThread
    protected void executeUseCase(@NonNull RxUseCase useCase, @NonNull SimpleSubscriber subscriber) {
        Preconditions.checkNotNull(useCase, "useCase");
        Preconditions.checkNotNull(subscriber, "subscriber");
        Timber.d("executeUseCase: %s", useCase.getClass().getSimpleName());
        useCase.execute(subscriber, Schedulers.from(JobExecutor.getThreadPoolExecutor()), AndroidSchedulers.mainThread());
    }

}
