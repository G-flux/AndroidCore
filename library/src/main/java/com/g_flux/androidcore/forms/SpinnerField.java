package com.g_flux.androidcore.forms;

import android.widget.Spinner;

/**
 * @author Kenneth Saey
 * @company G-flux
 * @since 03-07-2017 14:35
 */
public class SpinnerField<T> extends Field {

    public T value;

    public SpinnerField(String name, Integer fieldId) {
        super(name, fieldId);
    }

    @Override
    public void collect(Form form) {
        Spinner fieldView = (Spinner) getFieldView(form);
        if(fieldView != null) {
            value = (T) fieldView.getSelectedItem();
        }
    }
}
