package com.g_flux.androidcore.utils;

import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.g_flux.androidcore.R;

/**
 * @author Kenneth Saey
 * @company G-flux
 * @since 03-07-2017 14:35
 */

public class SnackbarUtils {
    public static void showNotYetImplementedSnack(View view) {
        showDismissableSnackBar(view, R.string.not_yet_implemented);
    }

    public static void showDismissableSnackBar(View view, String message) {
        showDismissableSnackBar(view, message, Snackbar.LENGTH_INDEFINITE);
    }

    public static void showDismissableSnackBar(View view, String message, int length) {
        final Snackbar snackbar = Snackbar.make(view, message, length);
        snackbar.setAction(R.string.action_dismiss, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar.dismiss();
            }
        });
        snackbar.show();
    }

    public static void showDismissableSnackBar(View view, @StringRes int message) {
        showDismissableSnackBar(view, message, Snackbar.LENGTH_INDEFINITE);
    }

    public static void showDismissableSnackBar(View view, @StringRes int message, int length) {
        final Snackbar snackbar = Snackbar.make(view, message, length);
        snackbar.setAction(R.string.action_dismiss, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar.dismiss();
            }
        });
        snackbar.show();
    }
}
