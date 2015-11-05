package com.marcohc.android.clean.architecture.presentation.view.impl.adapter.viewholder;

import android.content.Context;
import android.view.View;

import com.marcohc.android.clean.architecture.domain.model.BaseModel;
import com.marcohc.android.clean.architecture.presentation.view.impl.adapter.BaseListAdapter;

public abstract class ViewHolderAbstractClass {

    private BaseListAdapter.OnSubViewClickListener mOnSubViewClickListener;

    abstract public int getLayout();

    abstract public void findViewsById(View view);

    abstract public void initializeComponentBehavior(BaseModel item, Context context, int position);

    public void setup(BaseModel item, BaseModel previous_item, Context context, int position) {
        initializeComponentBehavior(item, context, position);
    }

    public void setOnSubViewClickListener(BaseListAdapter.OnSubViewClickListener onSubViewClickListener) {
        mOnSubViewClickListener = onSubViewClickListener;
    }

}
