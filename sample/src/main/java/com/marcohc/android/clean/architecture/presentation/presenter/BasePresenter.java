package com.marcohc.android.clean.architecture.presentation.presenter;

import android.util.Log;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.marcohc.android.clean.architecture.exception.DataException;
import com.marcohc.android.clean.architecture.common.util.Constants;
import com.marcohc.android.clean.architecture.presentation.view.inter.BaseView;

@SuppressWarnings("ConstantConditions")
public abstract class BasePresenter<V extends BaseView> extends MvpBasePresenter<V> {

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

    /**
     * Handles the error showing a fancy Toasteroid and logging the error
     *
     * @param error the data exception error
     */
    public void handleException(DataException error) {
        hideDialog();
        if (isViewAttached()) {
            showError(error.getMessage());
        }
        Log.e(Constants.LOG_TAG, "Exception: " + error.getMessage());
    }

    public void onEventMainThread(DataException exception) {
        handleException(exception);
    }
}
