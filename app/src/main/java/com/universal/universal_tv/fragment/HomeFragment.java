package com.universal.universal_tv.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.base.base.adapter.BaseViewPagerAdapter;
import com.base.tablayout.SlidingTabLayout;
import com.universal.universal_tv.R;
import com.universal.universal_tv.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页fragment
 */
public class HomeFragment extends BaseFragment {

    private SlidingTabLayout tabLayout;
    private ViewPager viewPager;
    private List<Fragment> fragments = new ArrayList<>();
    private final String[] mTitles = {"推荐","电影","电视剧","动漫","综艺"};
    private BaseViewPagerAdapter adapter;

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    protected void initStart(View view, @Nullable Bundle savedInstanceState) {
        tabLayout = findViewById(R.id.home_tabLayout);
        viewPager = findViewById(R.id.home_viewPager);
        fragments.clear();
        for (String title : mTitles)
            fragments.add(HomeChildFragment.newInstance(title));

        adapter = new BaseViewPagerAdapter<Fragment>(getChildFragmentManager(),fragments);
        viewPager.setAdapter(adapter);
        tabLayout.setViewPager(viewPager,mTitles);
    }

    @Override
    protected void loadData() {

    }
    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }


}
