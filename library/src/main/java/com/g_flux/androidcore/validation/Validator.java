package com.g_flux.androidcore.validation;

import android.view.View;

/**
 * @author Kenneth Saey
 * @company G-flux
 * @since 03-07-2017 14:35
 */
public abstract class Validator {

    protected String data;
    protected View form;

    public Validator(View form) {
        this.form = form;
    }

    public abstract boolean validate(String value);

    public Integer getError() {
        return null;
    }

    public String[] getErrorData() {
        return new String[0];
    }

    public void setData(String data) {
        this.data = data;
    }
}
