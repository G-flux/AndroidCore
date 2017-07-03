package com.g_flux.androidcore.validation;

import android.content.Context;
import android.os.Build;
import android.support.annotation.IdRes;
import android.text.Html;
import android.text.Spanned;
import android.util.SparseArray;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.g_flux.androidcore.interfaces.Transformer;
import com.g_flux.androidcore.utils.CollectionUtils;
import com.g_flux.androidcore.views.DateView;
import com.g_flux.androidcore.views.TimeView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Kenneth Saey
 * @company G-flux
 * @since 03-07-2017 14:35
 */
public abstract class FormValidator {

    private HashMap<String, Validator> validators;
    private SparseArray<ArrayList<ErrorMessage>> errors;
    private View form;

    private void initValidators(View form) {
        validators = new HashMap<>();
        validators.put("boolean", new BooleanValidator(form));
        validators.put("double", new DoubleValidator(form));
        validators.put("email", new EmailValidator(form));
        validators.put("equals_field", new EqualsFieldValidator(form));
        validators.put("equals", new EqualsValidator(form));
        validators.put("integer", new IntegerValidator(form));
        validators.put("required", new RequiredValidator(form));
    }

    public abstract SparseArray<Rule> rules();

    public SparseArray<ArrayList<ErrorMessage>> getErrors() {
        return errors;
    }

    public boolean validate(View form) {
        this.form = form;
        ensureValidators(form);
        clearErrors();
        SparseArray<Rule> rules = rules();
        if (rules != null) {
            for (int keyIndex = 0; keyIndex < rules.size(); keyIndex++) {
                Integer resourceId = rules.keyAt(keyIndex);
                Rule rule = rules.get(resourceId);
                ArrayList<Validator> validators = parseValidators(rule.getRule());
                if (!validators.isEmpty()) {
                    String fieldValue = getFieldValue(form, resourceId);
                    for (Validator validator : validators) {
                        if (!validator.validate(fieldValue)) {
                            if (errors.get(resourceId) == null) {
                                errors.put(resourceId, new ArrayList<ErrorMessage>());
                            }
                            errors.get(resourceId).add(new ErrorMessage(validator.getError(), rule.getErrorData()));
                        }
                    }
                }
            }
        }

        return errors.size() == 0;
    }

    public void showErrors(Context context, boolean isValid) {
        if (form != null && !isValid) {
            SparseArray<ArrayList<ErrorMessage>> errors = getErrors();
            for (int keyIndex = 0; keyIndex < errors.size(); keyIndex++) {
                Integer fieldResourceId = errors.keyAt(keyIndex);
                ArrayList<ErrorMessage> fieldErrors = errors.get(fieldResourceId);
                setFieldErrors(context, form, fieldResourceId, fieldErrors);
            }
        }
    }

    private void setFieldErrors(final Context context, View formView, @IdRes Integer fieldResourceId, ArrayList<ErrorMessage> errorMessages) {
        View field = formView.findViewById(fieldResourceId);
        if (field != null) {
            if (field instanceof EditText) {
                String errorString = CollectionUtils.implode(errorMessages, "<br />", new Transformer<ErrorMessage, String>() {
                    @Override
                    public String transform(ErrorMessage item) {
                        return item.toString(context);
                    }
                });
                Spanned spannedError;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    spannedError = Html.fromHtml(errorString, Html.FROM_HTML_MODE_COMPACT);
                } else {
                    spannedError = Html.fromHtml(errorString);
                }
                ((EditText) field).setError(spannedError);
            }
        }
    }

    private void ensureValidators(View form) {
        if (validators == null) {
            initValidators(form);
        }
    }

    private void clearErrors() {
        errors = new SparseArray<>();
    }

    private String getFieldValue(View form, @IdRes Integer resourceId) {
        if (form != null) {
            View field = form.findViewById(resourceId);
            if (field != null) {
                if (field instanceof EditText) {
                    return ((EditText) field).getText().toString();
                } else if (field instanceof Spinner) {
                    return ((Spinner) field).getSelectedItem().toString();
                } else if (field instanceof TimeView) {
                    return field.toString();
                } else if (field instanceof DateView) {
                    return field.toString();
                }
            }
        }

        return null;
    }

    private ArrayList<Validator> parseValidators(String validatorsString) {
        if (validatorsString != null && !validatorsString.isEmpty()) {
            String[] validatorKeys = validatorsString.split("\\|");
            if (validatorKeys.length > 0) {
                ArrayList<Validator> validatorList = new ArrayList<>();
                for (String singleValidatorString : validatorKeys) {
                    String[] validatorParts = singleValidatorString.split(":");
                    Validator validator = validators.get(validatorParts[0]);
                    if (validator != null) {
                        if (validatorParts.length > 1) {
                            validator.setData(validatorParts[1]);
                        }
                        validatorList.add(validator);
                    }
                }
                return validatorList;
            }
        }

        return new ArrayList<>();
    }
}
