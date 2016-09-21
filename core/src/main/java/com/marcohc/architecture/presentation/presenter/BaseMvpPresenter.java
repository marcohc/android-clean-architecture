package com.marcohc.architecture.presentation.presenter;

import android.support.annotation.NonNull;
import android.support.annotation.UiThread;

import com.marcohc.architecture.R;
import com.marcohc.architecture.data.executor.JobExecutor;
import com.marcohc.architecture.domain.exception.DomainException;
import com.marcohc.architecture.domain.interactor.BaseSubscriber;
import com.marcohc.architecture.domain.interactor.UseCase;
import com.marcohc.architecture.presentation.view.BaseMvpView;

import timber.log.Timber;

/**
 * Base presenter which contains common methods.
 * <p>
 * Override it for specific common methods in presenters.
 *
 * @param <V> the MvpView which will handle this presenter
 * @author Marco Hernaiz
 * @since 08/08/16
 */
public abstract class BaseMvpPresenter<V extends BaseMvpView> extends MvpNullObjectBasePresenter<V> {

    /**
     * To avoid dialog memory leaks.
     */
    @Override
    public void detachView(boolean retainInstance) {
        getView().hideDialog();
        super.detachView(retainInstance);
    }

    /**
     * Shows or hides dialog with "Loading..." text by default.
     */
    protected void showLoadingDialog() {
        getView().showDialog(getView().getString(R.string.loading));
    }

    /**
     * Shows dialog with message.
     *
     * @param message dialog message
     */
    protected void showDialog(String message) {
        getView().showDialog(message);
    }

    protected void hideDialog() {
        getView().hideDialog();
    }

    /**
     * Shows message.
     *
     * @param message the message to be shown
     */
    protected void showMessage(String message) {
        getView().showMessage(message);
    }

    /**
     * Shows error.
     *
     * @param message the message to be shown
     */
    protected void showError(String message) {
        getView().showError(message);
    }

    /**
     * Handles the error logging the error.
     *
     * @param exception the data exception error
     */
    public <E extends DomainException> void handleException(E exception) {
        hideDialog();
        Timber.e("Exception: %s", exception.getMessage());
    }

    /**
     * Executes a use case using the JobExecutor.
     * <p>
     * It'll be executed in the pool thread and returned in the MainThread
     *
     * @param useCase    use case to execute
     * @param subscriber the subscriber for Rx
     */
    @UiThread
    protected void executeUseCase(@NonNull UseCase useCase, @NonNull BaseSubscriber subscriber) {
        Timber.d("executeUseCase: %s", useCase.getClass().getSimpleName());
        JobExecutor.getInstance().executeUseCase(useCase, subscriber);
    }

}
