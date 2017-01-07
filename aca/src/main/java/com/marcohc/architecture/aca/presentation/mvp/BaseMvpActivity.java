package com.marcohc.architecture.aca.presentation.mvp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.hannesdorfmann.mosby.mvp.MvpActivity;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;

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

    /**
     * Used by Calligraphy.
     */
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    /**
     * @return The layout id that will be used on {@link AppCompatActivity#setContentView(int)}
     */
    @LayoutRes
    protected abstract int getLayoutId();

    @CallSuper
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        onViewCreated(savedInstanceState);
    }

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
     * Called from {@link AppCompatActivity#onCreate(Bundle)}
     * after {@link AppCompatActivity#setContentView(int)} and {@link ButterKnife#bind(Activity)} was called.
     * <p>
     * This method can be used for initialize {@link android.view.View}
     *
     * @param savedInstanceState If non-null, this activity is being re-constructed from a previous saved state as given here.
     */
    protected abstract void onViewCreated(@Nullable final Bundle savedInstanceState);
}
