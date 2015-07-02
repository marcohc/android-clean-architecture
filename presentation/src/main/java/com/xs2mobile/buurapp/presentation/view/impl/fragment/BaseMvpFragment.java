package com.xs2mobile.buurapp.presentation.view.impl.fragment;

import android.app.ProgressDialog;

import com.hannesdorfmann.mosby.mvp.MvpFragment;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.marcohc.toasteroid.helper.Toasteroid;
import com.xs2mobile.buurapp.R;
import com.xs2mobile.buurapp.domain.bus.BusProvider;
import com.xs2mobile.buurapp.presentation.view.inter.BaseView;

public abstract class BaseMvpFragment<V extends BaseView, P extends MvpPresenter<V>> extends MvpFragment<V, P> implements BaseView {

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
            dialog = ProgressDialog.show(getActivity(), "", getString(R.string.loading), true);
//            dialog.setCancelable(false);
        } else {
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }
        }
    }

    @Override
    public void showError(String error) {
        Toasteroid.show(getActivity(), error, Toasteroid.STYLES.ERROR);
    }

    @Override
    public String getResourceString(int stringId) {
        return getString(stringId);
    }

}
