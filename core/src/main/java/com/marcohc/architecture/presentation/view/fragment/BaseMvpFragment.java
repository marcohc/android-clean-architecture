package com.marcohc.architecture.presentation.view.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hannesdorfmann.mosby.mvp.MvpFragment;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.marcohc.architecture.R;
import com.marcohc.architecture.common.bus.BusProvider;
import com.marcohc.architecture.presentation.view.BaseView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseMvpFragment<V extends BaseView, P extends MvpPresenter<V>> extends MvpFragment<V, P> implements BaseView {

    private ProgressDialog dialog;
    private Unbinder unbinder;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int layoutRes = getLayoutRes();
        if (layoutRes == 0) {
            throw new IllegalArgumentException("getLayoutRes() returned 0, which is not allowed. If you don't want to use getLayoutRes() but implement your own view for this "
                    + "fragment manually, then you have to override onCreateView();");
        } else {
            return inflater.inflate(layoutRes, container, false);
        }
    }

    @CallSuper
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstance) {
        super.onViewCreated(view, savedInstance);
        unbinder = ButterKnife.bind(this, view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @LayoutRes
    protected abstract int getLayoutRes();

    @Override
    public void showLoadingDialog() {
        showDialog("", getString(R.string.loading), true);
    }

    @Override
    public void showLoadingDialog(boolean isCancelable) {
        showDialog("", getString(R.string.loading), isCancelable);
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
    public void showMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public String getQuantityString(int stringId, int quantity, Object... formatArgs) {
        return getResources().getQuantityString(stringId, quantity, formatArgs);
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
