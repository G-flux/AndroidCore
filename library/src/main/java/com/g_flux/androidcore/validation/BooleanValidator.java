package com.g_flux.androidcore.validation;

import android.view.View;

import com.g_flux.androidcore.R;

/**
 * @author Kenneth Saey
 * @company G-flux
 * @since 03-07-2017 14:35
 */
public class BooleanValidator extends Validator {

    public BooleanValidator(View form) {
        super(form);
    }

    @Override
    public boolean validate(String value) {
        return value == null || value.equalsIgnoreCase("true") || value.equalsIgnoreCase("false");
    }

    @Override
    public Integer getError() {
        return R.string.validation_error_boolean;
    }
}
