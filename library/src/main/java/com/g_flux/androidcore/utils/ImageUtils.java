package com.g_flux.androidcore.utils;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.Base64;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

/**
 * @author Kenneth Saey
 * @company G-flux
 * @since 03-07-2017 14:35
 */
public class ImageUtils {

    public static Bitmap getScaledBitmap(ImageView imageView, int maxWidth, int maxHeight) {
        Bitmap largeBitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        int width = largeBitmap.getWidth();
        int height = largeBitmap.getHeight();
        float scaleFactor = 1f;
        if (width > maxWidth) {
            scaleFactor = (float) maxWidth / (float) width;
        }
        if (height > maxHeight) {
            scaleFactor = Math.min(scaleFactor, (float) maxHeight / (float) height);
        }
        if (scaleFactor < 1f) {
            return Bitmap.createScaledBitmap(largeBitmap, Math.round((float) width * scaleFactor), Math.round((float) height * scaleFactor), false);
        }

        return largeBitmap;
    }

    public static String toBase64PNG(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return "data:image/png;base64," + Base64.encodeToString(stream.toByteArray(), 0);
    }
}
