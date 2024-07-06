package com.nshmura.recyclertablayout.listener;


import androidx.viewpager.widget.ViewPager;

import com.nshmura.recyclertablayout.RecyclerTabLayout;

public class ViewPagerOnPageChangeListener implements ViewPager.OnPageChangeListener {

    private int mScrollState;
    private final RecyclerTabLayout mRecyclerTabLayout;

    public ViewPagerOnPageChangeListener(RecyclerTabLayout recyclerTabLayout) {
        mRecyclerTabLayout = recyclerTabLayout;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        mRecyclerTabLayout.scrollToTab(position, positionOffset, false);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        mScrollState = state;
    }

    @Override
    public void onPageSelected(int position) {
        if (mScrollState == ViewPager.SCROLL_STATE_IDLE) {
            if (mRecyclerTabLayout.mIndicatorPosition != position) {
                mRecyclerTabLayout.scrollToTab(position);
            }
        }
    }
}
