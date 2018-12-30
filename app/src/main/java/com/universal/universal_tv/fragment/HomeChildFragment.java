package com.universal.universal_tv.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.universal.universal_tv.R;
import com.universal.universal_tv.adapter.HomeBannerAdapter;
import com.universal.universal_tv.adapter.HomeChildAdapter;
import com.universal.universal_tv.base.BaseFragment;
import com.universal.universal_tv.bean.HomeChildAdvertBean;
import com.universal.universal_tv.bean.HomeChildBean;
import com.universal.universal_tv.bean.HomeChildPosterBean;
import com.universal.universal_tv.mvp.view.HomeChildView;
import com.universal.universal_tv.ui.customView.BannerViewPager;
import com.universal.universal_tv.ui.customView.indicaor.PageViewIndicaor;

import java.util.ArrayList;
import java.util.List;

public class HomeChildFragment extends BaseFragment implements HomeChildView {
    private String title;
    private RecyclerView recyclerView;
    private View headView;
    private HomeChildAdapter adapter;
    private BannerViewPager banner;
    private HomeBannerAdapter bannerAdapter;
    private List<String> bannerImages = new ArrayList<>();
    private PageViewIndicaor indicaor;

    public static HomeChildFragment newInstance(String title) {
        HomeChildFragment fragment = new HomeChildFragment();
        Bundle args = new Bundle();
        args.putString("title",title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getString("title");
        }
    }

    @Override
    protected void initStart(View view, @Nullable Bundle savedInstanceState) {
        initRecyclerView();
    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.homeChild_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new HomeChildAdapter();
        initBanner();
        adapter.addHeaderView(headView);
        recyclerView.setAdapter(adapter);
    }

    private void initBanner() {
        headView = LayoutInflater.from(getContext()).inflate(R.layout.homechild_head,recyclerView,false);
        banner = headView.findViewById(R.id.homeChild_banner);
        bannerAdapter = new HomeBannerAdapter(bannerImages);
        banner.setBanndrAdapter(bannerAdapter);
        indicaor = headView.findViewById(R.id.homeChild_indicaor);
        indicaor.setViewPager(banner);
    }

    @Override
    protected void loadData() {
        List<String> list = new ArrayList<>();
        list.add("http://img0.imgtn.bdimg.com/it/u=340357347,773920023&fm=26&gp=0.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545989052894&di=dd2ffeceaf9b68dfde6e582a964343e6&imgtype=0&src=http%3A%2F%2Fimg3.xiazaizhijia.com%2Fwalls%2F20160506%2F1024x768_c96982a932b6079.jpg");
        upBannerData(list);

        List<HomeChildBean> list1 = new ArrayList<>();
        list1.add(new HomeChildAdvertBean());
        list1.add(new HomeChildPosterBean());
        list1.add(new HomeChildPosterBean());
        list1.add(new HomeChildAdvertBean());
        list1.addAll(list1);
        list1.addAll(list1);
        list1.addAll(list1);
        upRecyclerViewData(list1);

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home_child;
    }

    @Override
    //更新轮播图数据
    public void upBannerData(List<String> images) {
        bannerAdapter = new HomeBannerAdapter(images);
        banner.setBanndrAdapter(bannerAdapter);
        indicaor.setViewPager(banner,images.size());
    }

    @Override
    public void upRecyclerViewData(List<HomeChildBean> images) {
        adapter.setNewData(images);
    }

}
