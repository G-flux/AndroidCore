package com.g_flux.androidcore.validation;

import android.view.View;

import com.g_flux.androidcore.R;

/**
 * @author Kenneth Saey
 * @company G-flux
 * @since 03-07-2017 14:35
 */
public class EqualsValidator extends Validator {

    public EqualsValidator(View form) {
        super(form);
    }

    @Override
    public boolean validate(String value) {
        if (data == null) {
            return value == null;
        }

        return data.equals(value);
    }

    @Override
    public Integer getError() {
        return R.string.validation_error_equals;
    }

    @Override
    public String[] getErrorData() {
        return new String[]{data};
    }
}
