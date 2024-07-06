package com.nshmura.recyclertablayout.adapter;

import androidx.recyclerview.widget.RecyclerView;

public abstract class RecyclerTabAdapter<T extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<T> {

    protected int mIndicatorPosition;

    public void setCurrentIndicatorPosition(int indicatorPosition) {
        mIndicatorPosition = indicatorPosition;
    }

    public int getCurrentIndicatorPosition() {
        return mIndicatorPosition;
    }
}
