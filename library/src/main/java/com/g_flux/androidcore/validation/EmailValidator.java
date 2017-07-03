package com.g_flux.androidcore.validation;

import android.util.Patterns;
import android.view.View;

import com.g_flux.androidcore.R;

/**
 * @author Kenneth Saey
 * @project Febelnet
 * @company G-flux
 * @since 11-05-2017 11:02.
 */
public class EmailValidator extends Validator {

    public EmailValidator(View form) {
        super(form);
    }

    @Override
    public boolean validate(String value) {
        return value == null || value.isEmpty() || Patterns.EMAIL_ADDRESS.matcher(value).matches();
    }

    @Override
    public Integer getError() {
        return R.string.validation_error_email;
    }
}
