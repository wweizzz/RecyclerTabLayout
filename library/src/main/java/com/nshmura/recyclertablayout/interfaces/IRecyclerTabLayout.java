package com.nshmura.recyclertablayout.interfaces;

import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.nshmura.recyclertablayout.adapter.RecyclerTabAdapter;

public interface IRecyclerTabLayout {

    void setIndicatorColor(int indicatorColor);

    void setIndicatorHeight(int indicatorHeight);

    void setAutoSelectionMode(boolean autoSelect);

    void setPositionThreshold(float positionThreshold);

    void setUpWithAdapter(RecyclerTabAdapter<?> adapter, ViewPager viewPager);

    void setUpWithAdapter(RecyclerTabAdapter<?> adapter, ViewPager2 viewPager2);

    void scrollToTab(int position);

    void scrollToTab(int position, float positionOffset, boolean fitIndicator);

    void setCurrentItem(int position, boolean smoothScroll);
}
