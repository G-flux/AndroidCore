package com.g_flux.androidcore.forms;

import android.util.SparseArray;
import android.widget.RadioGroup;

/**
 * @author Kenneth Saey
 * @company G-flux
 * @since 03-07-2017 14:35
 */
public abstract class RadioGroupField<T extends Enum<T>> extends Field {

    public T value;

    public RadioGroupField(String name, Integer fieldId) {
        super(name, fieldId);
    }

    @Override
    public void collect(Form form) {
        RadioGroup fieldView = (RadioGroup) getFieldView(form);
        if (fieldView != null) {
            SparseArray<T> radioMap = getRadioMap(new SparseArray<T>());
            int checkedRadioId = fieldView.getCheckedRadioButtonId();
            value = radioMap.get(checkedRadioId);
        }
    }

    public abstract SparseArray<T> getRadioMap(SparseArray<T> radioMap);
}
