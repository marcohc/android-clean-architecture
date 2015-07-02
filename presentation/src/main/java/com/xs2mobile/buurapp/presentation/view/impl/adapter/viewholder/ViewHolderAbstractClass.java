package com.xs2mobile.buurapp.presentation.view.impl.adapter.viewholder;

import android.content.Context;
import android.view.View;

import com.xs2mobile.buurapp.presentation.view.impl.adapter.BaseListAdapter;
import com.xs2mobile.buurapp.presentation.model.BaseModel;


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
