package com.marcohc.architecture.recycler;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.marcohc.architecture.aca.domain.model.BaseJsonModel;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class BaseRecyclerAdapter<M extends BaseJsonModel, T extends BaseRecyclerViewHolder> extends RecyclerView.Adapter<T> {

    private final Context context;
    private final Class<? extends BaseRecyclerViewHolder> viewHolderClass;
    private final int layoutId;
    private List<M> items;
    private ItemViewClickListener listener;

    public BaseRecyclerAdapter(Context context, @LayoutRes int layoutId, Class<? extends BaseRecyclerViewHolder> viewHolderClass) {
        this.context = context;
        this.viewHolderClass = viewHolderClass;
        this.layoutId = layoutId;
        this.items = new ArrayList<>();
    }

    public BaseRecyclerAdapter(Context context, @LayoutRes int layoutId, Class<? extends BaseRecyclerViewHolder> viewHolderClass, List<M> items) {
        this.context = context;
        this.viewHolderClass = viewHolderClass;
        this.layoutId = layoutId;
        this.items = items;
    }

    public void addAll(List<M> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public void addItem(M item) {
        items.add(item);
        notifyItemInserted(items.size() - 1);
    }

    public void addItemAt(int position, M item) {
        items.add(position, item);
        notifyItemInserted(position);
    }

    public M getItem(int position) {
        return items.get(position);
    }

    // Create new views (invoked by the layout manager)
    @Override
    public T onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        BaseRecyclerViewHolder baseViewHolder = null;
        try {
            baseViewHolder = viewHolderClass.getConstructor(View.class).newInstance(view);
            baseViewHolder.bindViews(view, listener);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (T) baseViewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(T holder, int position) {
        holder.setUpView(context, items.get(position), position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setOnItemClickListener(ItemViewClickListener listener) {
        this.listener = listener;
    }

    public interface ItemViewClickListener {
        void onItemViewClick(View view, int position);
    }
}
