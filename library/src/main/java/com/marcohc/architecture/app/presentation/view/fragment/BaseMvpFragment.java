package com.marcohc.architecture.app.presentation.view.fragment;

import android.app.ProgressDialog;

import com.marcohc.architecture.R;
import com.marcohc.architecture.common.bus.BusProvider;
import com.marcohc.architecture.app.presentation.mosby.mvp.MvpFragment;
import com.marcohc.architecture.app.presentation.mosby.mvp.MvpPresenter;
import com.marcohc.architecture.app.presentation.view.BaseView;
import com.marcohc.toasteroid.Toasteroid;

public abstract class BaseMvpFragment<V extends BaseView, P extends MvpPresenter<V>> extends MvpFragment<V, P> implements BaseView {

    private ProgressDialog dialog;

    @Override
    public void onStart() {
        super.onStart();
        BusProvider.register(presenter);
    }

    @Override
    public void onStop() {
        BusProvider.unregister(presenter);
        super.onStop();
    }

    @Override
    public void showLoadingDialog() {
        showDialog("", getResourceString(R.string.loading), true);
    }

    @Override
    public void showLoadingDialog(boolean isCancelable) {
        showDialog("", getResourceString(R.string.loading), isCancelable);
    }

    @Override
    public void showDialog(String message) {
        showDialog("", message, true);
    }

    @Override
    public void showDialog(String message, boolean isCancelable) {
        showDialog("", message, isCancelable);
    }

    @Override
    public void showDialog(String title, String message) {
        showDialog(title, message, false);
    }

    @Override
    public void showDialog(String title, String message, boolean isCancelable) {
        hideDialog();
        dialog = ProgressDialog.show(getActivity(), title, message, true);
        dialog.setCancelable(isCancelable);
    }

    @Override
    public void hideDialog() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    @Override
    public void showSuccess(String message) {
        Toasteroid.show(getActivity(), message, Toasteroid.STYLES.SUCCESS);
    }

    @Override
    public void showInfo(String message) {
        Toasteroid.show(getActivity(), message, Toasteroid.STYLES.INFO);
    }

    @Override
    public void showDelete(String message) {
        Toasteroid.show(getActivity(), message, Toasteroid.STYLES.DELETE);
    }

    @Override
    public void showWarning(String warningMessage) {
        Toasteroid.show(getActivity(), warningMessage, Toasteroid.STYLES.WARNING);
    }

    @Override
    public void showError(String error) {
        Toasteroid.show(getActivity(), error, Toasteroid.STYLES.ERROR);
    }

    @Override
    public String getResourceString(int stringId) {
        return getString(stringId);
    }

    @Override
    public String getResourceString(int stringId, Object... formatArgs) {
        return getString(stringId, formatArgs);
    }

    /**
     * This method will be call by the parent activity when the back button is pressed
     *
     * @return true if fragment handle the event or false otherwise
     */
    public boolean onBackPressed() {
        return false;
    }

}
