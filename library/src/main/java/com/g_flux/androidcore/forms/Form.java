package com.g_flux.androidcore.forms;

import android.support.annotation.NonNull;
import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Kenneth Saey
 * @company G-flux
 * @since 03-07-2017 14:35
 */
public class Form {

    public View formView;
    public ArrayList<Field> fields;

    public Form(View formView, Field[] fields) {
        this.formView = formView;
        this.fields = new ArrayList<>(Arrays.asList(fields));
    }

    public void collect() {
        if (fields != null && formView != null) {
            for (Field field : fields) {
                field.collect(this);
            }
        }
    }

    public void reset() {
        if (fields != null && formView != null) {
            for (Field field : fields) {
                field.reset(this);
            }
        }
    }

    public void disable() {
        if (fields != null && formView != null) {
            for (Field field : fields) {
                field.disable(this);
            }
        }
    }

    public void enable() {
        if (fields != null && formView != null) {
            for (Field field : fields) {
                field.enable(this);
            }
        }
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        if (fields != null) {
            try {
                for (Field field : fields) {
                    switch (field.type) {
                        case UNDEFINED:
                        case STRING:
                        default:
                            json.put(field.name, field.stringValue);
                            break;
                        case INTEGER:
                            json.put(field.name, field.integerValue);
                            break;
                        case DOUBLE:
                            json.put(field.name, field.doubleValue);
                            break;
                        case BOOLEAN:
                            json.put(field.name, field.booleanValue);
                            break;
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return json;
    }

    public Field getField(@NonNull String name) {
        for (Field field : fields) {
            if (field.name.equals(name)) {
                return field;
            }
        }

        return null;
    }

    public String getString(@NonNull String name) {
        Field field = getField(name);
        if (field == null) {
            return null;
        }

        return field.stringValue;
    }

    public int getInt(@NonNull String name) {
        Field field = getField(name);
        if (field == null) {
            return 0;
        }

        return field.integerValue;
    }

    public double getDouble(@NonNull String name) {
        Field field = getField(name);
        if (field == null) {
            return 0d;
        }

        return field.doubleValue;
    }

    public boolean getBoolean(@NonNull String name) {
        Field field = getField(name);
        if (field == null) {
            return false;
        }

        return field.booleanValue;
    }
}
