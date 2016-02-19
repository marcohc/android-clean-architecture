package com.marcohc.android.clean.architecture.presentation.view.activity;

import android.app.ProgressDialog;
import android.content.Context;

import com.marcohc.android.clean.architecture.presentation.mosby.mvp.MvpActivity;
import com.marcohc.android.clean.architecture.presentation.mosby.mvp.MvpPresenter;
import com.marcohc.android.clean.architecture.R;
import com.marcohc.android.clean.architecture.common.bus.BusProvider;
import com.marcohc.android.clean.architecture.presentation.view.BaseView;
import com.marcohc.toasteroid.Toasteroid;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public abstract class BaseMvpActivity<V extends BaseView, P extends MvpPresenter<V>> extends MvpActivity<V, P> implements BaseView {

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
        dialog = ProgressDialog.show(this, title, message, true);
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
        Toasteroid.show(this, message, Toasteroid.STYLES.SUCCESS);
    }

    @Override
    public void showInfo(String message) {
        Toasteroid.show(this, message, Toasteroid.STYLES.INFO);
    }

    @Override
    public void showDelete(String message) {
        Toasteroid.show(this, message, Toasteroid.STYLES.DELETE);
    }

    @Override
    public void showWarning(String warningMessage) {
        Toasteroid.show(this, warningMessage, Toasteroid.STYLES.WARNING);
    }

    @Override
    public void showError(String error) {
        Toasteroid.show(this, error, Toasteroid.STYLES.ERROR);
    }

    @Override
    public String getResourceString(int stringId) {
        return getString(stringId);
    }

    @Override
    public String getResourceString(int stringId, Object... formatArgs) {
        return getString(stringId, formatArgs);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
