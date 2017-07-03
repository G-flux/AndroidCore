package com.g_flux.androidcore.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @author Kenneth Saey
 * @company G-flux
 * @since 03-07-2017 14:35
 */
public abstract class SortedViewHolder<O> extends RecyclerView.ViewHolder {

    public SortedViewHolder(View itemView) {
        super(itemView);
        buildView(itemView);
    }

    public abstract void buildView(View itemView);

    public void setTag(O object) {
        itemView.setTag(object);
    }
}
