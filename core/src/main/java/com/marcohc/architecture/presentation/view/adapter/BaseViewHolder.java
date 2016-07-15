package com.marcohc.architecture.presentation.view.adapter;

import android.content.Context;
import android.view.View;

import com.marcohc.architecture.domain.model.BaseJsonModel;

import butterknife.ButterKnife;

public abstract class BaseViewHolder<T extends BaseJsonModel> {

    private View.OnClickListener listener;

    public abstract void setUpView(Context context, T item, int position);

    public void bindViews(View view, View.OnClickListener listener) {
        this.listener = listener;
        ButterKnife.bind(this, view);
    }

    protected void onChildViewClick(View view) {
        if (listener != null) {
            listener.onClick(view);
        }
    }

}
