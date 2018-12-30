package com.universal.universal_tv.ui.customView.indicaor;

import android.support.v4.view.ViewPager;

public interface IBaseIndicaor extends ViewPager.OnPageChangeListener  {
    /** bind ViewPager */
    void setViewPager(ViewPager vp);

    /** for special viewpager,such as LooperViewPager */
    void setViewPager(ViewPager vp, int realCount);

    void setCurrentItem(int item);
}
