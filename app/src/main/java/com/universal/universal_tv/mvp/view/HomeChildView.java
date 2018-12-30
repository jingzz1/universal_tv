package com.universal.universal_tv.mvp.view;

import com.universal.universal_tv.bean.HomeChildBean;

import java.util.List;

public interface HomeChildView {
    //更新轮播图数据
    void upBannerData(List<String> images);
    //更新recyclerView数据
    void upRecyclerViewData(List<HomeChildBean> images);
}
