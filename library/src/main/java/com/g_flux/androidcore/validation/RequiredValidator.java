package com.g_flux.androidcore.validation;

import android.view.View;

import com.g_flux.androidcore.R;

/**
 * @author Kenneth Saey
 * @project Febelnet
 * @company G-flux
 * @since 11-05-2017 09:10.
 */
public class RequiredValidator extends Validator {

    public RequiredValidator(View form) {
        super(form);
    }

    @Override
    public boolean validate(String value) {
        return value != null && !value.isEmpty();
    }

    @Override
    public Integer getError() {
        return R.string.validation_error_required;
    }
}
