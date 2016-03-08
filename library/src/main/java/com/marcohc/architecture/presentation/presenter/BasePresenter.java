package com.marcohc.architecture.presentation.presenter;

import com.marcohc.architecture.domain.error.DomainError;
import com.marcohc.architecture.presentation.mosby.mvp.MvpBasePresenter;
import com.marcohc.architecture.presentation.view.BaseView;
import com.marcohc.toasteroid.Toasteroid;

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
        Toasteroid.dismiss();
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
    public void showSuccess(String message) {
        if (isViewAttached()) {
            getView().showSuccess(message);
        }
    }

    /**
     * Shows message
     *
     * @param message the message to be shown
     */
    public void showInfo(String message) {
        if (isViewAttached()) {
            getView().showInfo(message);
        }
    }

    /**
     * Shows delete message
     *
     * @param message the message to be shown
     */
    public void showDelete(String message) {
        if (isViewAttached()) {
            getView().showDelete(message);
        }
    }

    /**
     * Shows warning message
     *
     * @param warningMessage the error message to be shown
     */
    public void showWarning(String warningMessage) {
        if (isViewAttached()) {
            getView().showWarning(warningMessage);
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
    public void onEventMainThread(DomainError exception) {
        handleException(exception);
    }

    /**
     * Handles the error showing a fancy Toasteroid and logging the error
     *
     * @param error the data exception error
     */
    public void handleException(DomainError error) {
        hideDialog();
        if (isViewAttached()) {
            showError(error.getMessage());
        }
        Timber.e("Exception: %s", error.getMessage());
    }

}
