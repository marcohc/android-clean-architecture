package com.marcohc.architecture.presentation.bus.presentation.mvp;

import android.support.annotation.NonNull;
import android.support.annotation.UiThread;

import com.marcohc.architecture.presentation.bus.domain.executor.BusJobExecutor;
import com.marcohc.architecture.presentation.bus.domain.interactor.BusUseCase;
import com.marcohc.architecture.presentation.presenter.BaseMvpPresenter;
import com.marcohc.architecture.presentation.view.BaseMvpView;

import timber.log.Timber;

public abstract class BaseBusMvpPresenter<V extends BaseMvpView> extends BaseMvpPresenter<V> {

    /**
     * Executes a use case using the {@link com.marcohc.architecture.presentation.bus.domain.executor.BusJobExecutor}.
     * <p>
     * It'll be executed in the pool thread.
     *
     * @param useCase use case to execute
     */
    @UiThread
    protected void executeUseCase(@NonNull BusUseCase useCase) {
        Timber.d("executeUseCase: %s", useCase.getClass().getSimpleName());
        BusJobExecutor.getInstance().executeHighPriorityUseCase(useCase);
    }

}
