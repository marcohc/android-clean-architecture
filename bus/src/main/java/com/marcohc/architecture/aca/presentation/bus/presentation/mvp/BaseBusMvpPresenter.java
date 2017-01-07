package com.marcohc.architecture.aca.presentation.bus.presentation.mvp;

import android.support.annotation.NonNull;
import android.support.annotation.UiThread;

import com.marcohc.architecture.aca.presentation.bus.domain.exception.DomainException;
import com.marcohc.architecture.aca.presentation.bus.domain.interactor.BusUseCase;
import com.marcohc.architecture.aca.presentation.mvp.BaseMvpPresenter;
import com.marcohc.architecture.aca.presentation.mvp.BaseMvpView;
import com.marcohc.architecture.common.thread.JobExecutor;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import timber.log.Timber;

public abstract class BaseBusMvpPresenter<V extends BaseMvpView> extends BaseMvpPresenter<V> {

    /**
     * Executes a use case using a ThreadPool.
     * <p>
     * It'll be executed in the pool thread.
     *
     * @param useCase use case to execute
     */
    @UiThread
    protected void executeUseCase(@NonNull final BusUseCase useCase) {
        Timber.d("executeUseCase: %s", useCase.getClass().getSimpleName());
        JobExecutor.execute(new Runnable() {
            @Override
            public void run() {
                useCase.execute();
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onDomainException(DomainException domainException) {
        // Override this method and do your error handling there
    }

}
