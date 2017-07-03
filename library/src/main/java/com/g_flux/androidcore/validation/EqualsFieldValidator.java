package com.g_flux.androidcore.validation;

import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.g_flux.androidcore.R;

/**
 * @author Kenneth Saey
 * @company G-flux
 * @since 03-07-2017 14:35
 */
public class EqualsFieldValidator extends Validator {

    private View matchView;

    public EqualsFieldValidator(View form) {
        super(form);
    }

    @Override
    public boolean validate(String value) {
        String matchString = getViewValue();
        if (matchString == null) {
            return value == null;
        }

        return matchString.equals(value);
    }

    private String getViewValue() {
        matchView = form.findViewById(Integer.parseInt(data));
        if (matchView == null) {
            return null;
        }

        if (matchView instanceof EditText) {
            return ((EditText) matchView).getText().toString();
        } else if (matchView instanceof TextView) {
            return ((TextView) matchView).getText().toString();
        } else if (matchView instanceof Spinner) {
            return ((Spinner) matchView).getSelectedItem().toString();
        }

        return null;
    }

    @Override
    public Integer getError() {
        return R.string.validation_error_equals_field;
    }

    @Override
    public String[] getErrorData() {
        return new String[]{getViewValue()};
    }
}
