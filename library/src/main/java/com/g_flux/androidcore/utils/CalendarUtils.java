package com.g_flux.androidcore.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * @author Kenneth Saey
 * @company G-flux
 * @since 03-07-2017 14:35
 */
public class CalendarUtils {

    private static SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-y", Locale.ENGLISH);
    private static SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd-MM-y HH:mm", Locale.ENGLISH);

    public static Calendar calendarFromDateString(String date) {
        if (StringUtils.isEmpty(date)) {
            return null;
        }

        String[] dateParts = date.split("-");
        if (dateParts.length == 3) {
            Calendar calendar = Calendar.getInstance();
            CalendarUtils.setMidnight(calendar);
            calendar.set(Integer.parseInt(dateParts[2]), Integer.parseInt(dateParts[1]) - 1, Integer.parseInt(dateParts[0]));

            return calendar;
        }

        return null;
    }

    public static Calendar calendarFromReverseDateString(String date) {
        if (StringUtils.isEmpty(date)) {
            return null;
        }

        String[] dateParts = date.split("-");
        if (dateParts.length == 3) {
            Calendar calendar = Calendar.getInstance();
            CalendarUtils.setMidnight(calendar);
            calendar.set(Integer.parseInt(dateParts[0]), Integer.parseInt(dateParts[1]) - 1, Integer.parseInt(dateParts[2]));

            return calendar;
        }

        return null;
    }

    public static Calendar calendarFromTimeString(String time) {
        if (StringUtils.isEmpty(time)) {
            return null;
        }

        String[] timeParts = time.split(":");
        if (timeParts.length >= 2) {
            Calendar calendar = Calendar.getInstance();
            CalendarUtils.setFields(calendar, 0, Calendar.SECOND, Calendar.MILLISECOND);
            calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeParts[0]));
            calendar.set(Calendar.MINUTE, Integer.parseInt(timeParts[1]));

            return calendar;
        }

        return null;
    }

    public static String time(Calendar calendar) {
        if (calendar == null) {
            return "";
        }

        return timeFormat.format(calendar.getTime());
    }

    public static String date(Calendar calendar) {
        if (calendar == null) {
            return "";
        }

        return dateFormat.format(calendar.getTime());
    }

    public static String dateTime(Calendar calendar) {
        if (calendar == null) {
            return "";
        }

        return dateTimeFormat.format(calendar.getTime());
    }

    public static String addDays(String date, int days) {
        Calendar calendar = calendarFromDateString(date);
        if (calendar == null) {
            return "";
        }

        calendar.add(Calendar.DAY_OF_MONTH, days);

        return date(calendar);
    }

    protected static int getTimePart(String time, int partIndex) {
        if (StringUtils.isEmpty(time)) {
            return 0;
        }

        String[] parts = time.split(":");
        if (parts.length >= partIndex + 1) {
            return Integer.parseInt(parts[partIndex]);
        }

        return 0;
    }

    public static int hours(String time) {
        return getTimePart(time, 0);
    }

    public static int minutes(String time) {
        return getTimePart(time, 1);
    }

    public static int seconds(String time) {
        return getTimePart(time, 2);
    }

    public static int minutesBetween(String start, String end) {
        int startMinutes = hours(start) * 60 + minutes(start);
        int endMinutes = hours(end) * 60 + minutes(end);

        if (startMinutes > endMinutes) {
            return 0;
        }

        return endMinutes - startMinutes;
    }

    public static void setFields(Calendar calendar, int value, int... fields) {
        if (calendar == null) {
            calendar = Calendar.getInstance();
        }

        for (int field : fields) {
            calendar.set(field, value);
        }
    }

    public static void setMidnight(Calendar calendar) {
        if (calendar == null) {
            calendar = Calendar.getInstance();
        }

        CalendarUtils.setFields(calendar, 0, Calendar.HOUR, Calendar.MINUTE, Calendar.SECOND, Calendar.MILLISECOND);
    }

    public static Calendar getMidnight() {
        Calendar midnight = Calendar.getInstance();
        setMidnight(midnight);

        return midnight;
    }

    public static void transferFields(Calendar from, Calendar to, int... fields) {
        if (from == null) {
            from = Calendar.getInstance();
        }

        if (to == null) {
            to = Calendar.getInstance();
        }

        for (int field : fields) {
            to.set(field, from.get(field));
        }
    }

    public static void transferDate(Calendar from, Calendar to) {
        transferFields(from, to, Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH);
    }

    public static void transferTime(Calendar from, Calendar to) {
        transferFields(from, to, Calendar.HOUR, Calendar.MINUTE, Calendar.SECOND, Calendar.MILLISECOND);
    }
}
