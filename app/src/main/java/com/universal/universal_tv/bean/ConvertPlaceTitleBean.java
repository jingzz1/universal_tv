package com.universal.universal_tv.bean;

public class ConvertPlaceTitleBean extends ConvertPlaceBean{
   private String title = "未兑换特权";

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public int getItemType() {
        return titleType;
    }
}
