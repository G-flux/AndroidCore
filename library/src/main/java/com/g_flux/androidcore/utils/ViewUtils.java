package com.g_flux.androidcore.utils;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

/**
 * @author Kenneth Saey
 * @company G-flux
 * @since 03-07-2017 14:35
 */

public class ViewUtils {

    public static int pxToDp(int pixels, Context context) {
        return (int) (context.getResources().getDisplayMetrics().density * pixels + 0.5f);
    }

    public static void hideKeyboard(Context context, View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @ColorInt
    public static int getColor(Resources resources, @ColorRes int id, @Nullable Resources.Theme theme) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return resources.getColor(id, theme);
        }
        return resources.getColor(id);
    }

    public static void setTextConditionally(@NonNull TextView textView, String text, boolean show) {
        if (show) {
            textView.setText(text);
            textView.setVisibility(View.VISIBLE);
        } else {
            textView.setVisibility(View.INVISIBLE);
        }
    }

    public static void setTextConditionally(@NonNull TextView textView, String text) {
        setTextConditionally(textView, text, !StringUtils.isEmpty(text));
    }

    public static void enable(View... views) {
        for (View view : views) {
            view.setEnabled(true);
        }
    }

    public static void disable(View... views) {
        for (View view : views) {
            view.setEnabled(false);
        }
    }

    public static Spanned getHtml(String html) {
        if (StringUtils.isEmpty(html)) {
            return null;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(html, Html.FROM_HTML_MODE_COMPACT);
        } else {
            return Html.fromHtml(html);
        }
    }

    public static void setHtml(TextView textView, String html) {
        if (textView == null) {
            return;
        }

        if (StringUtils.isEmpty(html)) {
            textView.setText("");
        }

        textView.setText(getHtml(html));
    }
}
