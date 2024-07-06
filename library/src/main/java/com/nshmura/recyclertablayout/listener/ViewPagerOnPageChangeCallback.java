package com.nshmura.recyclertablayout.listener;

import androidx.viewpager2.widget.ViewPager2;

import com.nshmura.recyclertablayout.RecyclerTabLayout;

public class ViewPagerOnPageChangeCallback extends ViewPager2.OnPageChangeCallback {

    private int mScrollState;
    private final RecyclerTabLayout mRecyclerTabLayout;

    public ViewPagerOnPageChangeCallback(RecyclerTabLayout recyclerTabLayout) {
        mRecyclerTabLayout = recyclerTabLayout;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        super.onPageScrolled(position, positionOffset, positionOffsetPixels);
        mRecyclerTabLayout.scrollToTab(position, positionOffset, false);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        super.onPageScrollStateChanged(state);
        mScrollState = state;
    }

    @Override
    public void onPageSelected(int position) {
        super.onPageSelected(position);
        if (mScrollState == ViewPager2.SCROLL_STATE_IDLE) {
            if (mRecyclerTabLayout.mIndicatorPosition != position) {
                mRecyclerTabLayout.scrollToTab(position);
            }
        }
    }
}
