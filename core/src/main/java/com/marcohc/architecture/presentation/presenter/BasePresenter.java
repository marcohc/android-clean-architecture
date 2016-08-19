package com.marcohc.architecture.presentation.presenter;

import com.marcohc.architecture.domain.error.DomainException;
import com.marcohc.architecture.presentation.mosby.mvp.MvpBasePresenter;
import com.marcohc.architecture.presentation.view.BaseView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import timber.log.Timber;

@SuppressWarnings("ConstantConditions")
public abstract class BasePresenter<V extends BaseView> extends MvpBasePresenter<V> {

    @Override
    /**
     * To avoid toasts memory leaks
     */
    public void detachView(boolean retainInstance) {
        if (isViewAttached()) {
            getView().hideDialog();
        }
        super.detachView(retainInstance);
    }

    /**
     * Shows or hides dialog with "Loading..." text by default
     */
    public void showLoadingDialog() {
        if (isViewAttached()) {
            getView().showLoadingDialog();
        }
    }

    /**
     * Shows dialog with message
     *
     * @param message dialog message
     */
    public void showDialog(String message) {
        if (isViewAttached()) {
            getView().showDialog(message);
        }
    }

    /**
     * Shows dialog with message
     *
     * @param message dialog message
     */
    public void showDialog(String title, String message) {
        if (isViewAttached()) {
            getView().showDialog(title, message);
        }
    }

    /**
     * Shows dialog with message
     *
     * @param message dialog message
     */
    public void showDialog(String title, String message, boolean isCancelable) {
        if (isViewAttached()) {
            getView().showDialog(title, message, isCancelable);
        }
    }

    /**
     * Shows or hides dialog with "Loading..." text by default
     */
    public void hideDialog() {
        if (isViewAttached()) {
            getView().hideDialog();
        }
    }

    /**
     * Shows success message
     *
     * @param message the message to be shown
     */
    public void showMessage(String message) {
        if (isViewAttached()) {
            getView().showMessage(message);
        }
    }

    /**
     * Shows error message
     *
     * @param errorMessage the error message to be shown
     */
    public void showError(String errorMessage) {
        if (isViewAttached()) {
            getView().showError(errorMessage);
        }
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
        if (isViewAttached()) {
            showError(error.getMessage());
        }
        Timber.e("Exception: %s", error.getMessage());
    }

}
