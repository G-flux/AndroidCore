package com.g_flux.androidcore.utils;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * @author Kenneth Saey
 * @company G-flux
 * @since 03-07-2017 14:35
 */

public abstract class CustomViewContentArrayAdapter<T> extends ArrayAdapter<T> {

    public CustomViewContentArrayAdapter(@NonNull Context context, ArrayList<T> items) {
        super(context, android.R.layout.simple_spinner_item, items);
        setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }

    public CustomViewContentArrayAdapter(@NonNull Context context, T[] items) {
        super(context, android.R.layout.simple_spinner_item, items);
        setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getTextView(position, convertView, parent, android.R.layout.simple_spinner_item);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getTextView(position, convertView, parent, android.R.layout.simple_spinner_dropdown_item);
    }

    private View getTextView(int position, @Nullable View convertView, @NonNull ViewGroup parent, @LayoutRes int resourceId) {
        View view;
        TextView text;

        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        } else {
            view = convertView;
        }

        text = (TextView) view;

        T item = getItem(position);
        if (item != null) {
            text.setText(getItemValue(getContext(), item));
        }
        return view;
    }

    public abstract String getItemValue(Context context, T item);
}
