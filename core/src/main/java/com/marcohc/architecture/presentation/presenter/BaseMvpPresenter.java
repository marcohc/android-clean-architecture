package com.marcohc.architecture.presentation.presenter;

import com.marcohc.architecture.domain.error.DomainException;
import com.marcohc.architecture.presentation.view.BaseView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import timber.log.Timber;

@SuppressWarnings("ConstantConditions")
public abstract class BaseMvpPresenter<V extends BaseView> extends MvpNullObjectBasePresenter<V> {

    @Override
    /**
     * To avoid toasts memory leaks
     */
    public void detachView(boolean retainInstance) {
        getView().hideDialog();
        super.detachView(retainInstance);
    }

    /**
     * Shows or hides dialog with "Loading..." text by default
     */
    public void showLoadingDialog() {
        getView().showLoadingDialog();
    }

    /**
     * Shows dialog with message
     *
     * @param message dialog message
     */
    public void showDialog(String message) {
        getView().showDialog(message);
    }

    /**
     * Shows dialog with message
     *
     * @param message dialog message
     */
    public void showDialog(String title, String message) {
        getView().showDialog(title, message);
    }

    /**
     * Shows dialog with message
     *
     * @param message dialog message
     */
    public void showDialog(String title, String message, boolean isCancelable) {
        getView().showDialog(title, message, isCancelable);
    }

    /**
     * Shows or hides dialog with "Loading..." text by default
     */
    public void hideDialog() {
        getView().hideDialog();
    }

    /**
     * Shows success message
     *
     * @param message the message to be shown
     */
    public void showMessage(String message) {
        getView().showMessage(message);
    }

    /**
     * Shows error message
     *
     * @param errorMessage the error message to be shown
     */
    public void showError(String errorMessage) {
        getView().showError(errorMessage);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onDomainException(DomainException exception) {
        handleException(exception);
    }

    /**
     * Handles the error showing a fancy Toasteroid and logging the error
     *
     * @param error the data exception error
     */
    public void handleException(DomainException error) {
        hideDialog();
        showError(error.getMessage());
        Timber.e("Exception: %s", error.getMessage());
    }

}
