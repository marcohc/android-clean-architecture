package com.marcohc.android.clean.architecture.presentation;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.marcohc.android.clean.architecture.common.exception.AppError;
import com.marcohc.android.clean.architecture.presentation.view.BaseView;

import timber.log.Timber;

@SuppressWarnings("ConstantConditions")
public abstract class BasePresenter<V extends BaseView> extends MvpBasePresenter<V> {

    protected static final String LOG_TAG = "BasePresenter";

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
     * Shows error message
     *
     * @param errorMessage the error message to be shown
     */
    public void showError(String errorMessage) {
        if (isViewAttached()) {
            getView().showError(errorMessage);
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
     * Shows message
     *
     * @param message the message to be shown
     */
    public void showInfo(String message) {
        if (isViewAttached()) {
            getView().showInfo(message);
        }
    }

    public void onEventMainThread(AppError exception) {
        handleException(exception);
    }

    /**
     * Handles the error showing a fancy Toasteroid and logging the error
     *
     * @param error the data exception error
     */
    public void handleException(AppError error) {
        hideDialog();
        if (isViewAttached()) {
            showError(error.getMessage());
        }
        Timber.e("Exception: %s", error.getMessage());
    }

}
