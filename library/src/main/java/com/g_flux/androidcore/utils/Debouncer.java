package com.g_flux.androidcore.utils;

import android.os.Handler;

/**
 * @author Kenneth Saey
 * @company G-flux
 * @since 03-07-2017 14:35
 */
public class Debouncer {

    private final Object lock = new Object();
    private long mMinimumIntervalMillis;
    private Runnable mRunnable;

    private Handler mHandler;
    private Runnable mDebounceRunnable;

    public Debouncer(long minimumIntervalMillis) {
        this.mMinimumIntervalMillis = minimumIntervalMillis;
        mHandler = new Handler();
        mDebounceRunnable = new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    if (mRunnable != null) {
                        Runnable runnable = mRunnable;
                        mRunnable = null;
                        runnable.run();
                    }
                }
            }
        };
    }

    public void debounce(Runnable runnable) {
        synchronized (lock) {
            if (mRunnable != null) {
                mHandler.removeCallbacks(mDebounceRunnable);
            }
            mRunnable = runnable;
            mHandler.postDelayed(mDebounceRunnable, mMinimumIntervalMillis);
        }
    }
}
