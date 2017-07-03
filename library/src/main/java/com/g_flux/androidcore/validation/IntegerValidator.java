package com.g_flux.androidcore.validation;

import android.view.View;

import com.g_flux.androidcore.R;

/**
 * @author Kenneth Saey
 * @company G-flux
 * @since 03-07-2017 14:35
 */
public class IntegerValidator extends Validator {

    public IntegerValidator(View form) {
        super(form);
    }

    @Override
    public boolean validate(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NullPointerException ignored) {
            return true;
        } catch (NumberFormatException ingored) {
            return false;
        }
    }

    @Override
    public Integer getError() {
        return R.string.validation_error_integer;
    }
}
