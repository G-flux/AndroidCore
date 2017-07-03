package com.g_flux.androidcore.listeners;

import android.view.View;

import com.g_flux.androidcore.utils.SnackbarUtils;

/**
 * @author Kenneth Saey
 * @company G-flux
 * @since 03-07-2017 14:35
 */
public class NotYetImplementedClickListener implements View.OnClickListener {

    @Override
    public void onClick(View v) {
        SnackbarUtils.showNotYetImplementedSnack(v);
    }
}
