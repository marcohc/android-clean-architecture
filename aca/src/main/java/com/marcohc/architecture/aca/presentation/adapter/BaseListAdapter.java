package com.marcohc.architecture.aca.presentation.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.marcohc.architecture.aca.domain.model.Model;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

/**
 * Base adapter.
 *
 * @param <M> the model which will be rendered
 * @author Marco Hernaiz
 * @since 09/08/16
 */
public class BaseListAdapter<M extends Model> extends ArrayAdapter<M> {

    // Class
    private final Context mContext;
    private final int mLayoutId;
    private Class<? extends BaseViewHolder> mViewHolderClass;
    private ChildViewClickListener mListener;

    public BaseListAdapter(Context context, @LayoutRes int layoutId, Class<? extends BaseViewHolder> viewHolderClass) {
        super(context, layoutId, new ArrayList<M>());
        this.mContext = context;
        this.mLayoutId = layoutId;
        this.mViewHolderClass = viewHolderClass;
    }

    /**
     * Add all method in {@link ArrayAdapter} for previous Android versions.
     *
     * @param items list of items in the Adapter
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void addThemAll(List<M> items) {
        try {
            addAll(items);
        } catch (NoSuchMethodError e) {
            for (int i = 0; i < items.size(); i++) {
                add(items.get(i));
            }
        }
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View currentView, @NonNull ViewGroup parent) {
        if (null == currentView || null == currentView.getTag()) {
            return inflateView(position);
        } else {
            return recycleView(currentView, position);
        }
    }

    @SuppressWarnings("unchecked")
    private View inflateView(int position) {
        BaseViewHolder baseViewHolder;
        View view = null;
        try {
            view = LayoutInflater.from(getContext()).inflate(mLayoutId, null);
            baseViewHolder = mViewHolderClass.newInstance();
            baseViewHolder.bindViews(view, mListener);
            view.setTag(baseViewHolder);
            baseViewHolder.setUpView(mContext, getItem(position), position);
        } catch (InstantiationException e) {
            Timber.e("InstantiationException: %s", e);
        } catch (IllegalAccessException e) {
            Timber.e("IllegalAccessException: %s", e);
        }
        return view;
    }

    @SuppressWarnings("unchecked")
    private View recycleView(View currentView, int position) {
        BaseViewHolder baseViewHolder = (BaseViewHolder) currentView.getTag();
        baseViewHolder.setUpView(mContext, getItem(position), position);
        return currentView;
    }

    /**
     * Setter for {@link ChildViewClickListener}.
     *
     * @param listener the listener
     */
    public void setOnChildViewClick(ChildViewClickListener listener) {
        this.mListener = listener;
    }

    /**
     * Interface for child view events.
     */
    public interface ChildViewClickListener {

        /**
         * It must be called when a view inside of the {@link BaseViewHolder} reports an event.
         *
         * @param view the view which reports the event
         * @param position the position of that view
         */
        void onChildViewClick(View view, int position);
    }
}
