package com.marcohc.architecture.presentation.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.marcohc.architecture.domain.model.BaseJsonModel;

import butterknife.ButterKnife;

public abstract class BaseRecyclerViewHolder<T extends BaseJsonModel> extends RecyclerView.ViewHolder {

    private BaseRecyclerAdapter.ItemViewClickListener listener;

    public BaseRecyclerViewHolder(View itemView) {
        super(itemView);
    }

    public void bindViews(View view, final BaseRecyclerAdapter.ItemViewClickListener listener) {
        this.listener = listener;
        ButterKnife.bind(this, view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onItemViewClick(view, getAdapterPosition());
                }
            }
        });
    }

    public abstract void setUpView(Context context, T item, int position);

    protected void onChildViewClick(View view) {
        if (listener != null) {
            listener.onItemViewClick(view, getAdapterPosition());
        }
    }
}
