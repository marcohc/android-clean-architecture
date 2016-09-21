package com.marcohc.architecture.presentation.view.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.hannesdorfmann.mosby.mvp.MvpActivity;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.marcohc.architecture.R;
import com.marcohc.architecture.common.bus.BusProvider;
import com.marcohc.architecture.presentation.view.BaseView;

import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public abstract class BaseMvpActivity<V extends BaseView, P extends MvpPresenter<V>> extends MvpActivity<V, P> implements BaseView {

    private ProgressDialog dialog;

    /**
     * @return The layout id that will be used on {@link AppCompatActivity#setContentView(int)}
     */
    @LayoutRes
    protected abstract int getLayoutId();

    @CallSuper
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies();

        setContentView(getLayoutId());
        ButterKnife.bind(this);
        onViewCreated(savedInstanceState);
    }

    /**
     * Override and place your injection code here. Will be called at {@link AppCompatActivity#onCreate(Bundle)}
     * before super was called.
     */
    protected void injectDependencies() {

    }

    /**
     * Called from {@link AppCompatActivity#onCreate(Bundle)}
     * after {@link AppCompatActivity#setContentView(int)} and {@link ButterKnife#bind(Activity)} was called.
     * <p>
     * This method can be used for initialize {@link android.view.View}
     *
     * @param savedInstanceState If non-null, this activity is being re-constructed
     *                           from a previous saved state as given here.
     */
    protected abstract void onViewCreated(@Nullable final Bundle savedInstanceState);

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
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public String getQuantityString(int stringId, int quantity, Object... formatArgs) {
        return getResources().getQuantityString(stringId, quantity, formatArgs);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
