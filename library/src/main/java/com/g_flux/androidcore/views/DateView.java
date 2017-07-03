package com.g_flux.androidcore.views;

import android.app.DatePickerDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.DatePicker;

import java.util.Calendar;

/**
 * @author Kenneth Saey
 * @company G-flux
 * @since 03-07-2017 14:35
 */
public class DateView extends AppCompatTextView {

    public int day;
    public int month;
    public int year;

    private DatePickerDialog mDatePickerDialog;

    //<editor-fold desc="Constructors">
    public DateView(Context context) {
        super(context);
        setCalendar(Calendar.getInstance());
    }

    public DateView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setCalendar(Calendar.getInstance());
    }

    public DateView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setCalendar(Calendar.getInstance());
    }
    //</editor-fold>

    public void handleDialog() {
        if (mDatePickerDialog == null) {
            setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    showDatePickerDialog();
                }
            });
        }
    }

    public void setCalendar(Calendar calendar) {
        day = calendar.get(Calendar.DAY_OF_MONTH);
        month = calendar.get(Calendar.MONTH) + 1;
        year = calendar.get(Calendar.YEAR);
        updateDisplay();
    }

    public void setDate(int day, int month, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day);
        setCalendar(calendar);
    }

    public void setDate(String date) {
        String[] dateParts = date.split("-");
        if (dateParts.length == 3) {
            setDate(
                    Integer.parseInt(dateParts[0]),
                    Integer.parseInt(dateParts[1]),
                    Integer.parseInt(dateParts[2])
            );
        }
    }

    private void updateDisplay() {
        setText(toString());
    }

    private int forceInterval(int value, int min, int max) {
        return Math.min(Math.max(value, min), max);
    }

    //<editor-fold desc="Getters & Setters">
    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    private String getDayString() {
        return (day < 10 ? "0" : "") + day;
    }

    private String getMonthString() {
        return (month < 10 ? "0" : "") + month;
    }
    //</editor-fold>

    //<editor-fold desc="Dialogs">
    //<editor-fold desc="Time Picker Dialog">
    private void showDatePickerDialog() {
        if (mDatePickerDialog == null) {
            mDatePickerDialog = createDatePickerDialog();
        }
        mDatePickerDialog.show();
    }

    @NonNull
    private DatePickerDialog createDatePickerDialog() {
        return new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                setDate(dayOfMonth, month + 1, year);
            }
        }, year, month - 1, day);
    }
    //</editor-fold>
    //</editor-fold>

    @Override
    public String toString() {
        return getDayString() + "-" + getMonthString() + "-" + year;
    }
}
