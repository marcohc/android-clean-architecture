package com.marcohc.android.clean.architecture.presentation;

import android.util.Log;


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
    public void handleException(DataError error) {
        hideDialog();
        if (isViewAttached()) {
            if (error.getCode() == FirebaseError.NETWORK_ERROR) {
                showError(getView().getResourceString(R.string.internet_error));
            } else {
                showError(error.getMessage());
            }
        }

        Log.e(Constants.LOG_TAG, "Exception: " + error.getMessage());
    }

    public void onEventMainThread(DataError exception) {
        handleException(exception);
    }
}
