package com.g_flux.androidcore.listeners;

import android.view.View;

/**
 * @author Kenneth Saey
 * @company G-flux
 * @since 03-07-2017 14:35
 */
public interface RecyclerItemControlClickListener<E> {
    void onClick(View view, E item, int position);
}
