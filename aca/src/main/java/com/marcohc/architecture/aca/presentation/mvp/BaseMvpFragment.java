package com.marcohc.architecture.aca.presentation.mvp;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hannesdorfmann.mosby.mvp.MvpFragment;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;

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

    private Unbinder unbinder;

    /**
     * Return the layout resource like R.layout.my_layout
     *
     * @return the layout resource or zero ("0"), if you don't want to have an UI
     */
    @LayoutRes
    protected abstract int getLayoutRes();

    /**
     * This method will be call by the parent activity when the back button is pressed.
     *
     * @return true if fragment handle the event or false otherwise
     */
    public boolean onBackPressed() {
        return false;
    }

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

}
