package com.nshmura.recyclertablayout.listener;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nshmura.recyclertablayout.RecyclerTabLayout;

public class RecyclerOnScrollListener extends RecyclerView.OnScrollListener {

    protected RecyclerTabLayout mRecyclerTabLayout;
    protected LinearLayoutManager mLinearLayoutManager;

    public RecyclerOnScrollListener(RecyclerTabLayout recyclerTabLayout, LinearLayoutManager linearLayoutManager) {
        mRecyclerTabLayout = recyclerTabLayout;
        mLinearLayoutManager = linearLayoutManager;
    }

    public int mDx;

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        mDx += dx;
    }

    @Override
    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
        switch (newState) {
            case RecyclerView.SCROLL_STATE_IDLE:
                if (mDx > 0) {
                    selectCenterTabForRightScroll();
                } else {
                    selectCenterTabForLeftScroll();
                }
                mDx = 0;
                break;
            case RecyclerView.SCROLL_STATE_DRAGGING:
            case RecyclerView.SCROLL_STATE_SETTLING:
        }
    }

    protected void selectCenterTabForRightScroll() {
        int first = mLinearLayoutManager.findFirstVisibleItemPosition();
        int last = mLinearLayoutManager.findLastVisibleItemPosition();
        int center = mRecyclerTabLayout.getWidth() / 2;
        for (int position = first; position <= last; position++) {
            View view = mLinearLayoutManager.findViewByPosition(position);
            if (view != null && view.getLeft() + view.getWidth() >= center) {
                mRecyclerTabLayout.setCurrentItem(position, false);
                break;
            }
        }
    }

    protected void selectCenterTabForLeftScroll() {
        int first = mLinearLayoutManager.findFirstVisibleItemPosition();
        int last = mLinearLayoutManager.findLastVisibleItemPosition();
        int center = mRecyclerTabLayout.getWidth() / 2;
        for (int position = last; position >= first; position--) {
            View view = mLinearLayoutManager.findViewByPosition(position);
            if (view != null && view.getLeft() <= center) {
                mRecyclerTabLayout.setCurrentItem(position, false);
                break;
            }
        }
    }
}