package com.universal.universal_tv.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.animation.DecelerateInterpolator;

import com.universal.universal_tv.activity.HomeActivity;
import com.universal.universal_tv.java.FixedSpeedScroller;

import java.lang.reflect.Field;

/**
 * 这里写一些不好分类的方法
 */
public class MyAppUtils {

    /**
     * 修改viewPager 自动滑动时间
     *
     * @param context
     * @param viewpager
     * @param DurationSwitch
     */
    public static void controlViewPagerSpeed(Context context, ViewPager viewpager, int DurationSwitch) {
        try {
            Field mField;

            mField = ViewPager.class.getDeclaredField("mScroller");
            mField.setAccessible(true);

            FixedSpeedScroller mScroller = mScroller = new FixedSpeedScroller(context,
                    new DecelerateInterpolator());
            mScroller.setmDuration(DurationSwitch);
            mField.set(viewpager, mScroller);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 跳转到登录activity或首页
     * 需求：清空所有activity堆栈
     */
    public static void jumpHomeActivity(Context context, int showIndex) {
        Intent i = new Intent(context, HomeActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        Bundle bundle = new Bundle();
        bundle.putInt("showIndex",showIndex);
        i.putExtras(bundle);
        context.startActivity(i);
    }

}
