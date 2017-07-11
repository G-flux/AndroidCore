package com.g_flux.androidcore.views;

import android.support.annotation.ColorRes;
import android.support.annotation.StringRes;
import android.view.View;

import com.g_flux.androidcore.utils.ViewUtils;

/**
 * @author Kenneth Saey
 * @company G-flux
 * @since 11-07-2017 13:24.
 */
public abstract class ViewHolder {
    public View rootView;

    public ViewHolder(View rootView) {
        this.rootView = rootView;
    }

    public void build(BuildListener buildListener) {
        if (buildListener != null) {
            buildListener.onAfterBuild();
        }
    }

    public void onDestroy() {

    }

    public interface BuildListener {
        void onAfterBuild();
    }

    protected String getString(@StringRes int id, Object... formatArgs) {
        return rootView.getResources().getString(id, formatArgs);
    }

    protected int getColor(@ColorRes int id) {
        return ViewUtils.getColor(rootView.getResources(), id, null);
    }
}
