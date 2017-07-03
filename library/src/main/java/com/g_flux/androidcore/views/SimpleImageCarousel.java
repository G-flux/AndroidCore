package com.g_flux.androidcore.views;

import android.view.View;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;

/**
 * @author Kenneth Saey
 * @company G-flux
 * @since 03-07-2017 14:35
 */
public class SimpleImageCarousel {

    public ArrayList<String> images;
    protected ImageLoader mImageLoader;
    public int position = 0;

    public NetworkImageView imageView;
    public View buttonLeft;
    public View buttonRight;

    public SimpleImageCarousel(ArrayList<String> images, NetworkImageView imageView, ImageLoader imageLoader) {
        this.images = images;
        this.imageView = imageView;
        this.mImageLoader = imageLoader;
    }

    public void build() {
        if (images == null || images.isEmpty() || imageView == null) {
            return;
        }

        show(position);
    }

    public SimpleImageCarousel setLeft(View buttonLeft) {
        this.buttonLeft = buttonLeft;
        if (buttonLeft != null) {
            if (images.size() == 1) {
                buttonLeft.setVisibility(View.GONE);
            } else {
                buttonLeft.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        previous();
                    }
                });
            }
        }

        return this;
    }

    public SimpleImageCarousel setRight(View buttonRight) {
        this.buttonRight = buttonRight;
        if (buttonRight != null) {
            if (images.size() == 1) {
                buttonRight.setVisibility(View.GONE);
            } else {
                buttonRight.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        next();
                    }
                });
            }
        }

        return this;
    }

    private void previous() {
        if (position > 0) {
            position--;
        } else {
            position = images.size() - 1;
        }
        show(position);
    }

    private void next() {
        if (position < images.size() - 1) {
            position++;
        } else {
            position = 0;
        }
        show(position);
    }

    public void show(int position) {
        imageView.setImageUrl(images.get(position), mImageLoader);
    }
}
