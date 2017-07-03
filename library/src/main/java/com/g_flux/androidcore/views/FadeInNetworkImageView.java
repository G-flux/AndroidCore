package com.g_flux.androidcore.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.util.AttributeSet;

import com.android.volley.toolbox.NetworkImageView;
import com.g_flux.androidcore.utils.ViewUtils;

/**
 * @author Kenneth Saey
 * @company G-flux
 * @since 03-07-2017 14:35
 */
public class FadeInNetworkImageView extends NetworkImageView {

    public static final int FADE_IN_TIME_MILLIS = 400;

    public FadeInNetworkImageView(Context context) {
        super(context);
    }

    public FadeInNetworkImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FadeInNetworkImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setImageBitmap(Bitmap bitmap) {
        TransitionDrawable transitionDrawable = new TransitionDrawable(new Drawable[]{
                new ColorDrawable(ViewUtils.getColor(getResources(), android.R.color.transparent, null)),
                new BitmapDrawable(getContext().getResources(), bitmap)
        });

        transitionDrawable.setCrossFadeEnabled(true);
        setImageDrawable(transitionDrawable);
        transitionDrawable.startTransition(FADE_IN_TIME_MILLIS);
    }
}
