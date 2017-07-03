package com.g_flux.androidcore.listeners;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * @author Kenneth Saey
 * @company G-flux
 * @since 03-07-2017 14:35
 */

public class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {

    private OnItemClickListener mOnItemClickListener;
    private GestureDetector mGestureDetector;
    private ClickType clickType = ClickType.NONE;

    public RecyclerItemClickListener(Context context, OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
        mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                clickType = ClickType.SINGLE;
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                clickType = ClickType.LONG;
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        View childView = rv.findChildViewUnder(e.getX(), e.getY());
        if (childView != null && mOnItemClickListener != null) {
            mGestureDetector.onTouchEvent(e);
            switch (clickType) {
                case SINGLE:
                    mOnItemClickListener.onItemClick(childView, rv.getChildAdapterPosition(childView));
                    break;
                case LONG:
                    mOnItemClickListener.onItemLongClick(childView, rv.getChildAdapterPosition(childView));
                    break;
            }
            clickType = ClickType.NONE;
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    private enum ClickType {
        NONE, SINGLE, LONG
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }
}
