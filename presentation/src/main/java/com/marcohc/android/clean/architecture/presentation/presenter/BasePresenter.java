package com.marcohc.android.clean.architecture.presentation.presenter;

import android.util.Log;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.marcohc.android.clean.architecture.common.exception.DataException;
import com.marcohc.android.clean.architecture.common.util.Constants;
import com.marcohc.android.clean.architecture.presentation.view.inter.BaseView;

public abstract class BasePresenter<V extends BaseView> extends MvpBasePresenter<V> {

    /**
     * Shows or hides loading dialog
     *
     * @param hasToShow
     */
    public void showLoading(boolean hasToShow) {
        if (getView() != null) {
            getView().showLoading(hasToShow);
        }
    }

    /**
     * Shows error message
     *
     * @param errorMessage
     */
    public void showError(String errorMessage) {
        getView().showError(errorMessage);
    }

    /**
     * Handles the error showing a fancy Toasteroid and logging the error
     *
     * @param error
     */
    public void handleException(DataException error) {
        getView().showLoading(false);
        getView().showError(error.getMessage());
        Log.e(Constants.LOG_TAG, "Exception: " + error.getMessage());
    }

    public void onEventMainThread(DataException exception) {
        handleException(exception);
    }
}
