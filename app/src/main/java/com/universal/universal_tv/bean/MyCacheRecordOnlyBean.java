package com.universal.universal_tv.bean;

public class MyCacheRecordOnlyBean extends MyCacheRecordBaseBean {

    private String url;
    private String title = "标题很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长";

    @Override
    public String getUrl() {
        url = "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1724156208,1614384288&fm=26&gp=0.jpg";
        return url;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public int getItemType() {
        return onlyType;
    }
}
