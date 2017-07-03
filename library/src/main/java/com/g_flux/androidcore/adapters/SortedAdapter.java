package com.g_flux.androidcore.adapters;

import android.support.annotation.LayoutRes;
import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.util.SortedListAdapterCallback;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/**
 * @author Kenneth Saey
 * @company G-flux
 * @since 03-07-2017 14:35
 */
public abstract class SortedAdapter<O extends Comparable<O>, VH extends SortedViewHolder> extends RecyclerView.Adapter<VH> {

    protected SortedList<O> mObjects;
    protected SortedListAdapterCallback<O> mSortedListAdapterCallback;

    protected int mItemLayoutId;
    protected Class<VH> mViewHolderClass;

    public SortedAdapter(ArrayList<O> items, Class<O> objectClass, @LayoutRes int itemLayoutId, Class<VH> viewHolderClass) {
        mItemLayoutId = itemLayoutId;
        mViewHolderClass = viewHolderClass;
        mSortedListAdapterCallback = new SortedListAdapterCallback<O>(this) {
            @Override
            public int compare(O o1, O o2) {
                return o1.compareTo(o2);
            }

            @Override
            public boolean areContentsTheSame(O oldItem, O newItem) {
                return oldItem.equals(newItem);
            }

            @Override
            public boolean areItemsTheSame(O item1, O item2) {
                return item1.equals(item2);
            }
        };
        mObjects = new SortedList<>(objectClass, mSortedListAdapterCallback, items.size());
        mObjects.addAll(items);
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(mItemLayoutId, parent, false);
        try {
            // When the ViewHolder is a stand-alone class
            return mViewHolderClass.getConstructor(View.class).newInstance(view);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            try {
                // When the ViewHolder is an inner class of the Adapter
                return mViewHolderClass.getConstructor(this.getClass(), View.class).newInstance(this, view);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e2) {
                e.printStackTrace();
                return null;
            }
        }
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        O item = mObjects.get(position);
        holder.itemView.setTag(item);
        onBindViewHolder(holder, position, item);
    }

    public abstract void onBindViewHolder(VH holder, int position, O item);

    @Override
    public int getItemCount() {
        return mObjects == null ? 0 : mObjects.size();
    }

    public void replaceAll(ArrayList<O> items) {
        mObjects.clear();
        mObjects.addAll(items);
        notifyDataSetChanged();
    }
}
