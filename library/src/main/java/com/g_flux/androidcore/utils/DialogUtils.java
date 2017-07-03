package com.g_flux.androidcore.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;

import com.g_flux.androidcore.implementations.DialogDismisser;

/**
 * @author Kenneth Saey
 * @company G-flux
 * @since 03-07-2017 14:35
 */
public class DialogUtils {

    public static AlertDialog.Builder defaultBuilder(@NonNull Context context, int titleId, int messageId) {
        return defaultBuilder(context, context.getString(titleId), context.getString(messageId));
    }

    public static AlertDialog.Builder defaultBuilder(@NonNull Context context, int titleId, int messageId, int positiveButtonResourceId) {
        return defaultBuilder(context, context.getString(titleId), context.getString(messageId), positiveButtonResourceId);
    }

    public static AlertDialog.Builder defaultBuilder(@NonNull Context context, int titleId, int messageId, int positiveButtonResourceId, int negativeButtonResourceId) {
        return defaultBuilder(context, context.getString(titleId), context.getString(messageId), positiveButtonResourceId, negativeButtonResourceId);
    }

    public static AlertDialog.Builder defaultBuilder(@NonNull Context context, CharSequence title, CharSequence message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        if (title != null) {
            builder.setTitle(title);
        }
        if (message != null) {
            builder.setMessage(message);
        }

        return builder;
    }

    public static AlertDialog.Builder defaultBuilder(@NonNull Context context, CharSequence title, CharSequence message, int positiveButtonResourceId) {
        return defaultBuilder(context, title, message)
                .setPositiveButton(positiveButtonResourceId, new DialogDismisser());
    }

    public static AlertDialog.Builder defaultBuilder(@NonNull Context context, CharSequence title, CharSequence message, int positiveButtonResourceId, int negativeButtonResourceId) {
        return defaultBuilder(context, title, message, positiveButtonResourceId)
                .setNegativeButton(negativeButtonResourceId, new DialogDismisser());
    }
}
