package com.g_flux.androidcore.utils;

/**
 * @author Kenneth Saey
 * @company G-flux
 * @since 03-07-2017 14:35
 */
public class NumberUtils {

    public static int forceInterval(int number, int min, int max) {
        return Math.min(max, Math.max(min, number));
    }

    public static double forceInterval(double number, double min, double max) {
        return Math.min(max, Math.max(min, number));
    }
}
