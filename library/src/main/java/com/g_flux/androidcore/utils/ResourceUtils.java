package com.g_flux.androidcore.utils;

import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;

/**
 * @author Kenneth Saey
 * @company G-flux
 * @since 03-07-2017 14:35
 */
public class ResourceUtils {
    public static int getColor(@NonNull Resources resources, @ColorRes int id) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return resources.getColor(id, null);
        } else {
            return resources.getColor(id);
        }
    }

    public static int conditionalColor(boolean condition, @ColorRes int idIfTrue, @ColorRes int idIfFalse, @NonNull Resources resources) {
        return getColor(resources, condition ? idIfTrue : idIfFalse);
    }
}
