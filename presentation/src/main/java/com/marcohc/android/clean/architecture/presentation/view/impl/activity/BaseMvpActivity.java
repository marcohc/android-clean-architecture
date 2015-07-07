package com.marcohc.android.clean.architecture.presentation.view.impl.activity;

import android.app.ProgressDialog;
import android.content.Context;

import com.hannesdorfmann.mosby.mvp.MvpActivity;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.marcohc.android.clean.architecture.common.bus.BusProvider;
import com.marcohc.android.clean.architecture.presentation.R;
import com.marcohc.android.clean.architecture.presentation.view.inter.BaseView;
import com.marcohc.toasteroid.helper.Toasteroid;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public abstract class BaseMvpActivity<V extends BaseView, P extends MvpPresenter<V>> extends MvpActivity<V, P> implements BaseView {

    private ProgressDialog dialog;

    @Override
    public void onResume() {
        super.onResume();
        BusProvider.getInstance().register(presenter);
    }

    @Override
    public void onPause() {
        super.onPause();
        BusProvider.getInstance().unregister(presenter);
    }

    @Override
    public void showLoading(boolean show) {
        if (show) {
            dialog = ProgressDialog.show(this, "", getString(R.string.loading), true);
            dialog.setCancelable(true);
        } else {
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }
        }
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
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
