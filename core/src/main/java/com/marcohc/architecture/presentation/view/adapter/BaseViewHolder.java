package com.marcohc.architecture.presentation.view.adapter;

import android.content.Context;
import android.view.View;

import com.marcohc.architecture.domain.model.BaseModel;

import butterknife.ButterKnife;

public abstract class BaseViewHolder<T extends BaseModel> {

    abstract public int getLayout();

    abstract public void setUpView(Context context, T item, int position);

    public void bindViews(View view) {
        ButterKnife.bind(this, view);
    }

}
