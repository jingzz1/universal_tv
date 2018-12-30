package com.universal.universal_tv.bean;

public class HomeChildAdvertBean extends HomeChildBean {

    String image;

    public String getImage() {
        image = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545989052894&di=dd2ffeceaf9b68dfde6e582a964343e6&imgtype=0&src=http%3A%2F%2Fimg3.xiazaizhijia.com%2Fwalls%2F20160506%2F1024x768_c96982a932b6079.jpg";
        return image;
    }

    @Override
    public int getItemType() {
        return advert;
    }
}
