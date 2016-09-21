package com.marcohc.architecture.presentation.view.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.AnyThread;
import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.PluralsRes;
import android.support.annotation.UiThread;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hannesdorfmann.mosby.mvp.MvpFragment;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.marcohc.architecture.common.utils.Preconditions;
import com.marcohc.architecture.presentation.view.BaseMvpView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Base fragment which contains common methods.
 * <p>
 * Override it for specific common methods in fragments
 *
 * @param <V> the BaseMvpView type the superclass is implementing
 * @param <P> the type of MvpPresenter which will handle the logic of the class
 * @author Marco Hernaiz
 * @since 09/08/16
 */
public abstract class BaseMvpFragment<V extends BaseMvpView, P extends MvpPresenter<V>> extends MvpFragment<V, P> implements BaseMvpView {

    private final Handler mainThreadHandler = new Handler(Looper.getMainLooper());
    @Nullable
    private ProgressDialog dialog;
    private Unbinder unbinder;

    /**
     * Return the layout resource like R.layout.my_layout
     *
     * @return the layout resource or zero ("0"), if you don't want to have an UI
     */
    @LayoutRes
    protected abstract int getLayoutRes();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int layoutRes = getLayoutRes();
        if (layoutRes == 0) {
            throw new IllegalArgumentException("getLayoutRes() returned 0, which is not allowed. "
                    + "If you don't want to use getLayoutRes() but implement your own view for this "
                    + "fragment manually, then you have to override onCreateView();");
        } else {
            return inflater.inflate(layoutRes, container, false);
        }
    }

    /**
     * Override to bind views with ButterKnife.
     */
    @CallSuper
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
    }

    /**
     * Override to unbind views with ButterKnife.
     */
    @CallSuper
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    /**
     * This method will be call by the parent activity when the back button is pressed.
     *
     * @return true if fragment handle the event or false otherwise
     */
    public boolean onBackPressed() {
        return false;
    }

    /**
     * Run runnable in main thread.
     *
     * @param runnable the runnable to execute
     */
    @AnyThread
    protected void runOnUiThreadIfFragmentAlive(@NonNull final Runnable runnable) {
        Preconditions.checkNotNull(runnable, "runnable");

        if (Looper.myLooper() == Looper.getMainLooper() && isFragmentAlive()) {
            runnable.run();
        } else {
            mainThreadHandler.post(new Runnable() {
                @Override
                public void run() {
                    if (isFragmentAlive()) {
                        runnable.run();
                    }
                }
            });
        }
    }

    /**
     * Check if fragment still alive (attached, added, not removing, activity not null and view still not null.
     *
     * @return <code>true</code> if fragment still alive, other wise <code>false</code>
     */
    private boolean isFragmentAlive() {
        boolean isAlive = getActivity() != null && isAdded() && !isDetached();
        return isAlive && getView() != null && !isRemoving();
    }

    // ************************************************************************************************************************************************************************
    // * BaseMvpView methods
    // ************************************************************************************************************************************************************************

    @UiThread
    @Override
    public void showDialog(@Nullable String message) {
        if (isFragmentAlive()) {
            hideDialog();
            dialog = ProgressDialog.show(getActivity(), null, message);
            dialog.show();
        }
    }

    @UiThread
    @Override
    public void showDialog(String title, String message, boolean isCancelable) {
        if (isFragmentAlive()) {
            hideDialog();
            dialog = ProgressDialog.show(getActivity(), title, message, true);
            dialog.setCancelable(isCancelable);
        }
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
    public void showMessage(@Nullable String message) {
        if (isFragmentAlive()) {
            Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        }
    }

    @NonNull
    @Override
    public String getQuantityString(@PluralsRes int stringId, int quantity, Object... formatArgs) {
        return getResources().getQuantityString(stringId, quantity, formatArgs);
    }
}
