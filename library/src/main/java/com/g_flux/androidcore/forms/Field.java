package com.g_flux.androidcore.forms;

import android.support.annotation.ArrayRes;
import android.support.annotation.IdRes;
import android.view.View;
import android.widget.Checkable;
import android.widget.EditText;
import android.widget.Spinner;

import com.g_flux.androidcore.views.DateView;
import com.g_flux.androidcore.views.TimeView;

/**
 * @author Kenneth Saey
 * @company G-flux
 * @since 03-07-2017 14:35
 */
public class Field {

    public String name;
    public String stringValue;
    public boolean booleanValue;
    public int integerValue;
    public double doubleValue;

    protected Type type = Type.UNDEFINED;

    @IdRes
    protected Integer fieldId;
    @ArrayRes
    private Integer spinnerValuesArrayId;

    public Field(String name, Integer fieldId) {
        this.name = name;
        this.fieldId = fieldId;
    }

    public Field(String name, Integer fieldId, Type type) {
        this.name = name;
        this.fieldId = fieldId;
        this.type = type;
    }

    public Field(String name, Integer fieldId, Integer spinnerValuesArrayId) {
        this.name = name;
        this.fieldId = fieldId;
        this.spinnerValuesArrayId = spinnerValuesArrayId;
    }

    protected View getFieldView(Form form) {
        if (form.formView != null && fieldId != null) {
            return form.formView.findViewById(fieldId);
        }

        return null;
    }

    public void collect(Form form) {
        View fieldView = getFieldView(form);
        if (fieldView != null) {
            if (fieldView instanceof EditText) {
                stringValue = ((EditText) fieldView).getText().toString();
                if (type == Type.UNDEFINED) {
                    type = Type.STRING;
                } else {
                    switch (type) {
                        case INTEGER:
                            try {
                                integerValue = Integer.parseInt(stringValue);
                            } catch (NumberFormatException e) {
                                integerValue = 0;
                            }
                            break;
                        case DOUBLE:
                            try {
                                doubleValue = Double.parseDouble(stringValue);
                            } catch (NumberFormatException e) {
                                doubleValue = 0d;
                            }
                            break;
                        case BOOLEAN:
                            booleanValue = Boolean.parseBoolean(stringValue);
                            break;
                    }
                }
            } else if (fieldView instanceof TimeView) {
                type = Type.STRING;
                stringValue = fieldView.toString();
            } else if (fieldView instanceof DateView) {
                type = Type.STRING;
                stringValue = fieldView.toString();
            } else if (fieldView instanceof Spinner) {
                type = Type.STRING;
                if (spinnerValuesArrayId != null) {
                    stringValue = form.formView.getResources().getStringArray(spinnerValuesArrayId)[((Spinner) fieldView).getSelectedItemPosition()];
                } else {
                    stringValue = ((Spinner) fieldView).getSelectedItem().toString();
                }
            } else if (fieldView instanceof Checkable) {
                type = Type.BOOLEAN;
                booleanValue = ((Checkable) fieldView).isChecked();
            }
        }
    }

    public void reset(Form form) {
        View fieldView = getFieldView(form);
        if (fieldView != null) {
            if (fieldView instanceof EditText) {
                ((EditText) fieldView).setText("");
            } else if (fieldView instanceof Spinner) {
                ((Spinner) fieldView).setSelection(0);
            }
        }
    }

    public void disable(Form form) {
        View fieldView = getFieldView(form);
        if (fieldView != null) {
            fieldView.setEnabled(false);
        }
    }

    public void enable(Form form) {
        View fieldView = getFieldView(form);
        if (fieldView != null) {
            fieldView.setEnabled(true);
        }
    }

    public enum Type {
        UNDEFINED, STRING, INTEGER, DOUBLE, BOOLEAN
    }
}
