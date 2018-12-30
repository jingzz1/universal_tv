package com.universal.universal_tv.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public abstract class MyCacheRecordBaseBean implements MultiItemEntity {
    public static final int listType = 0;
    public static final int onlyType = 1;

    public abstract String getUrl();
    public abstract String getTitle();
}
