package com.nshmura.recyclertablayout.adapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

public class DefaultRecyclerTabAdapter extends RecyclerTabAdapter<DefaultRecyclerTabAdapter.ViewHolder> {

    protected static final int MAX_TAB_TEXT_LINES = 2;

    private ViewPager mViewPager;
    protected int mTabPaddingStart;
    protected int mTabPaddingTop;
    protected int mTabPaddingEnd;
    protected int mTabPaddingBottom;
    protected int mTabTextAppearance;
    protected boolean mTabSelectedTextColorSet;
    protected int mTabSelectedTextColor;
    private int mTabMaxWidth;
    private int mTabMinWidth;
    private int mTabBackgroundResId;
    private int mTabOnScreenLimit;

    public DefaultRecyclerTabAdapter(ViewPager viewPager) {
        mViewPager = viewPager;
    }

    @NonNull
    public DefaultRecyclerTabAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TabTextView tabTextView = new TabTextView(parent.getContext());

        if (mTabSelectedTextColorSet) {
            tabTextView.setTextColor(tabTextView.createColorStateList(
                    tabTextView.getCurrentTextColor(), mTabSelectedTextColor));
        }

        ViewCompat.setPaddingRelative(tabTextView, mTabPaddingStart, mTabPaddingTop,
                mTabPaddingEnd, mTabPaddingBottom);
        tabTextView.setTextAppearance(parent.getContext(), mTabTextAppearance);
        tabTextView.setGravity(Gravity.CENTER);
        tabTextView.setMaxLines(MAX_TAB_TEXT_LINES);
        tabTextView.setEllipsize(TextUtils.TruncateAt.END);

        if (mTabOnScreenLimit > 0) {
            int width = parent.getMeasuredWidth() / mTabOnScreenLimit;
            tabTextView.setMaxWidth(width);
            tabTextView.setMinWidth(width);

        } else {
            if (mTabMaxWidth > 0) {
                tabTextView.setMaxWidth(mTabMaxWidth);
            }
            tabTextView.setMinWidth(mTabMinWidth);
        }

        tabTextView.setTextAppearance(tabTextView.getContext(), mTabTextAppearance);
        if (mTabSelectedTextColorSet) {
            tabTextView.setTextColor(tabTextView.createColorStateList(
                    tabTextView.getCurrentTextColor(), mTabSelectedTextColor));
        }
        if (mTabBackgroundResId != 0) {
            tabTextView.setBackgroundDrawable(
                    AppCompatResources.getDrawable(tabTextView.getContext(), mTabBackgroundResId));
        }
        tabTextView.setLayoutParams(createLayoutParamsForTabs());
        return new ViewHolder(tabTextView);
    }

    @Override
    public void onBindViewHolder(DefaultRecyclerTabAdapter.ViewHolder holder, int position) {
        CharSequence title = mViewPager.getAdapter().getPageTitle(position);
        holder.title.setText(title);
        holder.title.setSelected(getCurrentIndicatorPosition() == position);
    }

    @Override
    public int getItemCount() {
        return mViewPager.getAdapter().getCount();
    }

    public void setTabPadding(int tabPaddingStart, int tabPaddingTop, int tabPaddingEnd,
                              int tabPaddingBottom) {
        mTabPaddingStart = tabPaddingStart;
        mTabPaddingTop = tabPaddingTop;
        mTabPaddingEnd = tabPaddingEnd;
        mTabPaddingBottom = tabPaddingBottom;
    }

    public void setTabTextAppearance(int tabTextAppearance) {
        mTabTextAppearance = tabTextAppearance;
    }

    public void setTabSelectedTextColor(boolean tabSelectedTextColorSet,
                                        int tabSelectedTextColor) {
        mTabSelectedTextColorSet = tabSelectedTextColorSet;
        mTabSelectedTextColor = tabSelectedTextColor;
    }

    public void setTabMaxWidth(int tabMaxWidth) {
        mTabMaxWidth = tabMaxWidth;
    }

    public void setTabMinWidth(int tabMinWidth) {
        mTabMinWidth = tabMinWidth;
    }

    public void setTabBackgroundResId(int tabBackgroundResId) {
        mTabBackgroundResId = tabBackgroundResId;
    }

    public void setTabOnScreenLimit(int tabOnScreenLimit) {
        mTabOnScreenLimit = tabOnScreenLimit;
    }

    protected RecyclerView.LayoutParams createLayoutParamsForTabs() {
        return new RecyclerView.LayoutParams(RecyclerView.LayoutParams.WRAP_CONTENT, RecyclerView.LayoutParams.MATCH_PARENT);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView title;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        mViewPager.setCurrentItem(pos, true);
                    }
                }
            });
        }
    }

    public static class TabTextView extends AppCompatTextView {

        public TabTextView(Context context) {
            super(context);
        }

        public ColorStateList createColorStateList(int defaultColor, int selectedColor) {
            final int[][] states = new int[2][];
            final int[] colors = new int[2];
            states[0] = SELECTED_STATE_SET;
            colors[0] = selectedColor;
            // Default enabled state
            states[1] = EMPTY_STATE_SET;
            colors[1] = defaultColor;
            return new ColorStateList(states, colors);
        }
    }
}
