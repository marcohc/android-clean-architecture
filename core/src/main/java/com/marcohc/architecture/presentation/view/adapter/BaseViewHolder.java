package com.marcohc.architecture.presentation.view.adapter;

import android.content.Context;
import android.view.View;

import com.marcohc.architecture.domain.model.BaseJsonModel;

import butterknife.ButterKnife;

public abstract class BaseViewHolder<T extends BaseJsonModel> {

    private BaseListAdapter.ChildViewClickListener listener;

    public abstract void setUpView(Context context, T item, int position);

    public void bindViews(View view, BaseListAdapter.ChildViewClickListener listener) {
        this.listener = listener;
        ButterKnife.bind(this, view);
    }

    protected void onChildViewClick(View view, int position) {
        if (listener != null) {
            listener.onChildViewClick(view, position);
        }
    }

}
