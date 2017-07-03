package com.g_flux.androidcore.validation;

import android.content.Context;
import android.support.annotation.StringRes;

/**
 * @author Kenneth Saey
 * @company G-flux
 * @since 03-07-2017 14:35
 */
public class ErrorMessage {

    @StringRes
    private Integer errorMessageResourceId;

    private String[] errorData;

    public ErrorMessage(@StringRes Integer errorMessageResourceId, String[] errorData) {
        this.errorMessageResourceId = errorMessageResourceId;
        this.errorData = errorData;
    }

    public String toString(Context context) {
        return context.getString(errorMessageResourceId, (Object) errorData);
    }
}
