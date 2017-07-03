package com.g_flux.androidcore.utils;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

/**
 * @author Kenneth Saey
 * @company G-flux
 * @since 03-07-2017 14:35
 */

public class IntentUtils {

    public static void putParcelable(Intent intent, String key, Parcelable parcelable) {
        if (intent != null && key != null) {
            intent.putExtra(key, ParcelableUtils.marshall(parcelable));
        }
    }

    public static <T extends Parcelable> T getParcelable(Intent intent, String key, Parcelable.Creator<T> creator) {
        if (intent == null || key == null || !intent.hasExtra(key)) {
            return null;
        }

        byte[] bytes = intent.getByteArrayExtra(key);

        return ParcelableUtils.unmarshall(bytes, creator);
    }

    public static void logExtras(Intent intent) {
        if (intent == null) {
            return;
        }

        Bundle extras = intent.getExtras();
        if (extras == null) {
            return;
        }

        for (String key : extras.keySet()) {
            Object value = extras.get(key);
            if (value == null) {
            } else {
            }
        }
    }
}
