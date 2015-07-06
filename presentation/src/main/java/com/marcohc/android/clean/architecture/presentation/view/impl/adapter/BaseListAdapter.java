package com.marcohc.android.clean.architecture.presentation.view.impl.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.marcohc.android.clean.architecture.domain.model.BaseModel;
import com.marcohc.android.clean.architecture.presentation.view.impl.adapter.viewholder.ViewHolderAbstractClass;

import java.util.List;

public class BaseListAdapter extends ArrayAdapter<BaseModel> {

    // ************************************************************************************************************************************************************************
    // * Attributes
    // ************************************************************************************************************************************************************************

    // Class

    private OnSubViewClickListener mCaller;
    private Class<? extends ViewHolderAbstractClass> viewHolderClass;

    // ************************************************************************************************************************************************************************
    // * Initialization methods
    // ************************************************************************************************************************************************************************

    public BaseListAdapter(OnSubViewClickListener caller, int resource, List<? extends BaseModel> objects, Class<? extends ViewHolderAbstractClass> viewHolderClass) {
        super(caller.getContext(), resource, (List<BaseModel>) objects);
        this.viewHolderClass = viewHolderClass;
        mCaller = caller;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolderAbstractClass viewHolder = null;
        if (null == convertView || null == convertView.getTag()) {
            try {
                viewHolder = viewHolderClass.newInstance();
                convertView = LayoutInflater.from(getContext()).inflate(viewHolder.getLayout(), null);
                viewHolder.findViewsById(convertView);
                convertView.setTag(viewHolder);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            viewHolder = (ViewHolderAbstractClass) convertView.getTag();
        }

        BaseModel previousItem = position > 0 ? getItem(position - 1) : null;
        viewHolder.setup(getItem(position), previousItem, mCaller.getContext(), position);

        return convertView;
    }

    public void onSubViewClick(View view, int position) {
        if (null != view && null != mCaller) {
            mCaller.onSubViewItemClick(view, position, null);
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void addThemAll(List<? extends BaseModel> items) {
        try {
            addAll(items);
        } catch (NoSuchMethodError e) {
            for (int i = 0; i < items.size(); i++) {
                add(items.get(i));
            }
        }
    }

    public interface OnSubViewClickListener {

        void onSubViewItemClick(View view, int position, Object data);

        Context getContext();
    }
}
