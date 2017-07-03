package com.g_flux.androidcore.forms;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;

import com.g_flux.androidcore.utils.ImageUtils;

/**
 * @author Kenneth Saey
 * @company G-flux
 * @since 03-07-2017 14:35
 */

public class ImageField extends Field {

    protected int maxWidth;
    protected int maxHeight;

    public ImageField(String name, Integer fieldId, int maxWidth, int maxHeight) {
        super(name, fieldId);
        this.maxWidth = maxWidth;
        this.maxHeight = maxHeight;
    }

    public ImageField(String name, Integer fieldId) {
        super(name, fieldId);
    }

    @Override
    public void collect(Form form) {
        ImageView fieldView = (ImageView) getFieldView(form);
        if (fieldView != null) {
            Bitmap bitmap = getBitmap(fieldView);
            if (bitmap != null) {
                stringValue = ImageUtils.toBase64PNG(bitmap);
            }
        }
    }

    private Bitmap getBitmap(ImageView fieldView) {
        if (fieldView != null) {
            if (maxWidth <= 0 || maxHeight <= 0) {
                return ((BitmapDrawable) fieldView.getDrawable()).getBitmap();
            }

            return ImageUtils.getScaledBitmap(fieldView, maxWidth, maxHeight);
        }

        return null;
    }

    @Override
    public void reset(Form form) {
        ImageView fieldView = (ImageView) getFieldView(form);
        if (fieldView != null) {
            fieldView.setImageDrawable(null);
        }
    }

    @Override
    public void disable(Form form) {
    }

    @Override
    public void enable(Form form) {
    }
}
