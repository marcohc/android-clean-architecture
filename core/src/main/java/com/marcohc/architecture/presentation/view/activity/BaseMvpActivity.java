package com.marcohc.architecture.presentation.view.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.AnyThread;
import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.PluralsRes;
import android.support.annotation.UiThread;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.hannesdorfmann.mosby.mvp.MvpActivity;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.marcohc.architecture.presentation.bus.BusProvider;
import com.marcohc.architecture.presentation.view.BaseMvpView;

import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Base activity which contains common methods.
 * <p>
 * Override it for specific common methods in activities.
 *
 * @param <V> the BaseMvpView type the superclass is implementing
 * @param <P> the type of MvpPresenter which will handle the logic of the class
 * @author Marco Hernaiz
 * @since 08/08/16
 */
public abstract class BaseMvpActivity<V extends BaseMvpView, P extends MvpPresenter<V>> extends MvpActivity<V, P> implements BaseMvpView {

    // ************************************************************************************************************************************************************************
    // * Attributes
    // ************************************************************************************************************************************************************************

    @Nullable
    private ProgressDialog dialog;

    // ************************************************************************************************************************************************************************
    // * Attributes
    // ************************************************************************************************************************************************************************

    /**
     * @return The layout id that will be used on {@link AppCompatActivity#setContentView(int)}
     */
    @LayoutRes
    protected abstract int getLayoutId();

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
     * @param savedInstanceState If non-null, this activity is being re-constructed from a previous saved state as given here.
     */
    protected abstract void onViewCreated(@Nullable final Bundle savedInstanceState);

    /**
     * When content is changed, ButterKnife will bind the view so is not needed to call from outside.
     */
    @Override
    @CallSuper
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
    }

    /**
     * Used by Calligraphy.
     */
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    // ************************************************************************************************************************************************************************
    // * BaseMvpView methods
    // ************************************************************************************************************************************************************************

    @UiThread
    @Override
    public void showDialog(String message) {
        showDialog("", message, true);
    }

    @UiThread
    @Override
    public void showDialog(String title, String message, boolean isCancelable) {
        hideDialog();
        dialog = ProgressDialog.show(this, title, message, true);
        dialog.setCancelable(isCancelable);
    }

    @UiThread
    @Override
    public void hideDialog() {
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }

    @UiThread
    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @AnyThread
    @NonNull
    @Override
    public String getQuantityString(@PluralsRes int stringId, int quantity, Object... formatArgs) {
        return getResources().getQuantityString(stringId, quantity, formatArgs);
    }
}
