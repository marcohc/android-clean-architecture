package com.marcohc.architecture.presentation.view.adapter;

import android.content.Context;
import android.view.View;

import com.marcohc.architecture.domain.model.Model;

import butterknife.ButterKnife;

/**
 * Base fragment which contains common methods.
 * <p>
 * Override it for specific common methods in fragments
 *
 * @param <M> the model which will be rendered
 * @author Marco Hernaiz
 * @since 09/08/16
 */
public abstract class BaseViewHolder<M extends Model> {

    private BaseListAdapter.ChildViewClickListener mListener;

    /**
     * Called by the {@link BaseListAdapter} to set up the view.
     *
     * @param context  the context
     * @param model    the model
     * @param position the position
     */
    protected abstract void setUpView(Context context, M model, int position);

    void bindViews(View view, BaseListAdapter.ChildViewClickListener listener) {
        this.mListener = listener;
        ButterKnife.bind(this, view);
    }

    protected void onChildViewClick(View view, int position) {
        if (mListener != null) {
            mListener.onChildViewClick(view, position);
        }
    }

}
