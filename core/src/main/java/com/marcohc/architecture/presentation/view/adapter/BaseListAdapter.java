package com.marcohc.architecture.presentation.view.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.marcohc.architecture.domain.model.BaseModel;

import java.util.ArrayList;
import java.util.List;

public class BaseListAdapter<T extends BaseModel> extends ArrayAdapter<T> {

    // ************************************************************************************************************************************************************************
    // * Attributes
    // ************************************************************************************************************************************************************************

    // Class
    private final Context context;
    private Class<? extends BaseViewHolder> viewHolderClass;

    // ************************************************************************************************************************************************************************
    // * Initialization methods
    // ************************************************************************************************************************************************************************

    public BaseListAdapter(Context context, int resource, Class<? extends BaseViewHolder> viewHolderClass) {
        super(context, resource, new ArrayList<T>());
        this.context = context;
        this.viewHolderClass = viewHolderClass;
    }

    public BaseListAdapter(Context context, int resource, List<T> items, Class<? extends BaseViewHolder> viewHolderClass) {
        super(context, resource, items);
        this.context = context;
        this.viewHolderClass = viewHolderClass;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        BaseViewHolder baseViewHolder = null;
        if (null == convertView || null == convertView.getTag()) {
            try {
                baseViewHolder = viewHolderClass.newInstance();
                convertView = LayoutInflater.from(getContext()).inflate(baseViewHolder.getLayout(), null);
                baseViewHolder.bindViews(convertView);
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
}
