package com.xs2mobile.buurapp.presentation.view.impl.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.xs2mobile.buurapp.presentation.model.BaseModel;
import com.xs2mobile.buurapp.presentation.view.impl.adapter.viewholder.ViewHolderAbstractClass;
import com.xs2mobile.buurapp.presentation.util.Constants;
import com.xs2mobile.buurapp.presentation.util.Constants.DATA_TYPE;

import java.util.List;

public class BaseListAdapter extends ArrayAdapter<BaseModel> {

    // ************************************************************************************************************************************************************************
    // * Attributes
    // ************************************************************************************************************************************************************************

    // Class

    private OnSubViewClickListener mCaller;
    private Constants.DATA_TYPE mItemType;

    // ************************************************************************************************************************************************************************
    // * Initialization methods
    // ************************************************************************************************************************************************************************

    @SuppressWarnings("unchecked")
    public BaseListAdapter(OnSubViewClickListener caller, int resource, List<? extends BaseModel> objects, Constants.DATA_TYPE itemType) {
        super(caller.getContext(), resource, (List<BaseModel>) objects);
        mItemType = itemType;
        mCaller = caller;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolderAbstractClass viewHolder;
        if (null == convertView || null == convertView.getTag()) {
            viewHolder = getItem(position).getDefaultViewHolder(mItemType);
            convertView = LayoutInflater.from(getContext()).inflate(viewHolder.getLayout(), null);
            viewHolder.findViewsById(convertView);
            convertView.setTag(viewHolder);
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

    public DATA_TYPE getType() {
        return mItemType;
    }

    public void setType(DATA_TYPE type) {
        mItemType = type;
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
