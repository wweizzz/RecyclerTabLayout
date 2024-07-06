package com.nshmura.recyclertablayout.demo.customview01;

import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.nshmura.recyclertablayout.adapter.RecyclerTabAdapter;
import com.nshmura.recyclertablayout.demo.ColorItem;
import com.nshmura.recyclertablayout.demo.DemoColorPagerAdapter;
import com.nshmura.recyclertablayout.demo.R;

import androidx.viewpager.widget.ViewPager;

/**
 * Created by Shinichi Nishimura on 2015/07/22.
 */
public class DemoCustomView01AdapterRecycler extends RecyclerTabAdapter<DemoCustomView01AdapterRecycler.ViewHolder> {

    private ViewPager mViewPager;
    private DemoColorPagerAdapter mAdapater;

    public DemoCustomView01AdapterRecycler(ViewPager viewPager) {
        mViewPager = viewPager;
        mAdapater = (DemoColorPagerAdapter) mViewPager.getAdapter();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_custom_view01_tab, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ColorItem colorItem = mAdapater.getColorItem(position);
        holder.title.setText(colorItem.name);
        holder.color.setBackgroundColor(colorItem.color);

        SpannableString name = new SpannableString(colorItem.name);
        if (position == getCurrentIndicatorPosition()) {
            name.setSpan(new StyleSpan(Typeface.BOLD), 0, name.length(), 0);
        }
        holder.title.setText(name);
    }

    @Override
    public int getItemCount() {
        return mAdapater.getCount();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public View color;
        public TextView title;

        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            color = itemView.findViewById(R.id.color);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mViewPager.setCurrentItem(getAdapterPosition());
                }
            });
        }
    }
}
