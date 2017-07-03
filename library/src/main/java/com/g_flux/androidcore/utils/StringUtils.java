package com.g_flux.androidcore.utils;

import java.util.Arrays;

/**
 * @author Kenneth Saey
 * @company G-flux
 * @since 03-07-2017 14:35
 */

public class StringUtils {

    public static String normalizePhoneNumber(String phoneNumber) {
        if (phoneNumber == null) {
            return null;
        }
        return phoneNumber.replaceAll("[^\\d\\+]", "");
    }

    public static boolean satisfiesQuery(String haystack, String query, boolean caseSensitive) {
        return caseSensitive && haystack.contains(query) || haystack.toLowerCase().contains(query.toLowerCase());
    }

    public static boolean isEmpty(String string) {
        return string == null || string.isEmpty();
    }

    public static boolean hasValue(String string) {
        return string != null && !string.isEmpty();
    }

    public static boolean containsDigit(String string) {
        return string != null && !string.isEmpty() && string.matches("[^0-9]*[0-9]+.*");
    }

    public static String simpleDouble(double number) {
        if (number == Math.round(number)) {
            return String.valueOf((int) number);
        }

        return String.valueOf(number);
    }

    public static String concat(String separator, String... parts) {
        if (parts.length == 0) {
            return "";
        }

        return CollectionUtils.implode(Arrays.asList(parts), separator);
    }
}
