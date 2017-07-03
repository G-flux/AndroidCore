package com.g_flux.androidcore.listeners;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * @author Kenneth Saey
 * @company G-flux
 * @since 03-07-2017 14:35
 */
public class InfiniteScrollListener extends RecyclerView.OnScrollListener {

    private boolean mIsLoading;
    private LinearLayoutManager mLinearLayoutManager;
    private int mThreshold;
    private OnLoadMoreListener mOnLoadMoreListener;

    public InfiniteScrollListener(LinearLayoutManager mLinearLayoutManager, int threshold, OnLoadMoreListener onLoadMoreListener) {
        this.mLinearLayoutManager = mLinearLayoutManager;
        this.mThreshold = threshold;
        this.mOnLoadMoreListener = onLoadMoreListener;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        if (mOnLoadMoreListener != null && !mIsLoading) {
            int totalItemCount = mLinearLayoutManager.getItemCount();
            int lastVisiblePosition = mLinearLayoutManager.findLastVisibleItemPosition();
            if (totalItemCount <= (lastVisiblePosition + mThreshold)) {
                mIsLoading = true;
                mOnLoadMoreListener.onLoadMore();
            }
        }
    }

    public void setLoading(boolean isLoading) {
        mIsLoading = isLoading;
    }
}
