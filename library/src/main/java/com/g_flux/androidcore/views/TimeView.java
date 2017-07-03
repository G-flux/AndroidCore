package com.g_flux.androidcore.views;

import android.app.TimePickerDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * @author Kenneth Saey
 * @company G-flux
 * @since 03-07-2017 14:35
 */
public class TimeView extends android.support.v7.widget.AppCompatTextView {

    private int hours;
    private int minutes;

    private TimePickerDialog mTimePickerDialog;

    //<editor-fold desc="Constructors">
    public TimeView(Context context) {
        super(context);
        setCalendar(Calendar.getInstance());
    }

    public TimeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setCalendar(Calendar.getInstance());
    }

    public TimeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setCalendar(Calendar.getInstance());
    }
    //</editor-fold>

    public void handleDialog() {
        if (mTimePickerDialog == null) {
            setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    showTimePickerDialog();
                }
            });
        }
    }

    public void setCalendar(Calendar calendar) {
        hours = calendar.get(Calendar.HOUR_OF_DAY);
        minutes = calendar.get(Calendar.MINUTE);
        updateDisplay();
    }

    public void setTime(int hours, int minutes) {
        this.hours = forceInterval(hours, 0, 23);
        this.minutes = forceInterval(minutes, 0, 59);
        updateDisplay();
    }

    public void setTime(String time) {
        String[] timeParts = time.split(":");
        if (timeParts.length == 2) {
            setTime(Integer.parseInt(timeParts[0]), Integer.parseInt(timeParts[1]));
        }
    }

    private void updateDisplay() {
        setText(toString());
    }

    private int forceInterval(int value, int min, int max) {
        return Math.min(Math.max(value, min), max);
    }

    //<editor-fold desc="Getters & Setters">
    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
        updateDisplay();
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
        updateDisplay();
    }

    private String getHoursString() {
        return (hours < 10 ? "0" : "") + hours;
    }

    private String getMinutesString() {
        return (minutes < 10 ? "0" : "") + minutes;
    }
    //</editor-fold>

    //<editor-fold desc="Dialogs">
    //<editor-fold desc="Time Picker Dialog">
    private void showTimePickerDialog() {
        if (mTimePickerDialog == null) {
            mTimePickerDialog = createTimePickerDialog();
        }
        mTimePickerDialog.show();
    }

    @NonNull
    private TimePickerDialog createTimePickerDialog() {
        return new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                setTime(hourOfDay, minute);
            }
        }, hours, minutes, true);
    }
    //</editor-fold>
    //</editor-fold>


    @Override
    public String toString() {
        return getHoursString() + ":" + getMinutesString();
    }
}
