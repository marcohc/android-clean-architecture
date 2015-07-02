package com.xs2mobile.buurapp.presentation.presenter;

import android.util.Log;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.xs2mobile.buurapp.data.datasource.DataError;
import com.xs2mobile.buurapp.data.util.NetworkManager;
import com.xs2mobile.buurapp.domain.interactor.inter.Interactor;
import com.xs2mobile.buurapp.presentation.view.inter.BaseView;

public abstract class BasePresenter<V extends BaseView, I> extends MvpBasePresenter<V> {

    private I interactor;

    public abstract I createInteractor();

    public I getInteractor() {
        if (interactor == null) {
            interactor = createInteractor();
        }
        return interactor;
    }

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
    public void handleError(DataError error) {
        getView().showLoading(false);
        getView().showError(error.getMessage());
        Log.e(NetworkManager.LOG_TAG, "Exception: " + error.getMessage());
    }
}
