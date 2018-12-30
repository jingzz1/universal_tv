package com.universal.universal_tv.bean;

import java.util.ArrayList;
import java.util.List;

public class MyCacheRecordListBean extends MyCacheRecordBaseBean {

    private List<String> caches = new ArrayList<>();
    private String title;

    public MyCacheRecordListBean() {
        caches.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1546146912473&di=b38297a9fab8c5b6e3b506f32a6689b9&imgtype=0&src=http%3A%2F%2Fimg.mp.itc.cn%2Fupload%2F20161209%2F0ce185165e734caca0e58aaf00f0c765_th.jpg");
        caches.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1546146912473&di=b38297a9fab8c5b6e3b506f32a6689b9&imgtype=0&src=http%3A%2F%2Fimg.mp.itc.cn%2Fupload%2F20161209%2F0ce185165e734caca0e58aaf00f0c765_th.jpg");
        caches.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1546146912473&di=b38297a9fab8c5b6e3b506f32a6689b9&imgtype=0&src=http%3A%2F%2Fimg.mp.itc.cn%2Fupload%2F20161209%2F0ce185165e734caca0e58aaf00f0c765_th.jpg");
    }

    public List<String> getCaches() {

        return caches;
    }

    @Override
    public int getItemType() {
        return listType;
    }

    @Override
    public String getUrl() {
        if(caches.size()>0)
            return caches.get(0);
        return "";
    }

    @Override
    public String getTitle() {
        title = "陈情令";
        return title;
    }
}
