package com.marcohc.architecture.presentation.view.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.marcohc.architecture.domain.model.BaseJsonModel;

import java.util.ArrayList;
import java.util.List;

public class BaseListAdapter<T extends BaseJsonModel> extends ArrayAdapter<T> {

    // ************************************************************************************************************************************************************************
    // * Attributes
    // ************************************************************************************************************************************************************************

    // Class
    private final Context context;
    private final int layoutId;
    private Class<? extends BaseViewHolder> viewHolderClass;
    private ChildViewClickListener listener;

    // ************************************************************************************************************************************************************************
    // * Initialization methods
    // ************************************************************************************************************************************************************************

    public BaseListAdapter(Context context, @LayoutRes int layoutId, Class<? extends BaseViewHolder> viewHolderClass) {
        super(context, layoutId, new ArrayList<T>());
        this.context = context;
        this.layoutId = layoutId;
        this.viewHolderClass = viewHolderClass;
    }

    public BaseListAdapter(Context context, @LayoutRes int layoutId, Class<? extends BaseViewHolder> viewHolderClass, List<T> items) {
        super(context, layoutId, items);
        this.context = context;
        this.layoutId = layoutId;
        this.viewHolderClass = viewHolderClass;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        BaseViewHolder baseViewHolder = null;
        if (null == convertView || null == convertView.getTag()) {
            try {
                convertView = LayoutInflater.from(getContext()).inflate(layoutId, null);
                baseViewHolder = viewHolderClass.newInstance();
                baseViewHolder.bindViews(convertView, listener);
                convertView.setTag(baseViewHolder);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            baseViewHolder = (BaseViewHolder) convertView.getTag();
        }

        baseViewHolder.setUpView(context, getItem(position), position);

        return convertView;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void addThemAll(List<T> items) {
        try {
            addAll(items);
        } catch (NoSuchMethodError e) {
            for (int i = 0; i < items.size(); i++) {
                add(items.get(i));
            }
        }
    }

    public void setOnChildViewClick(ChildViewClickListener listener) {
        this.listener = listener;
    }

    public interface ChildViewClickListener {
        void onChildViewClick(View view, int position);
    }
}
