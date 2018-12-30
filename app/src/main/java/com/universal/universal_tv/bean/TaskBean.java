package com.universal.universal_tv.bean;

import com.universal.universal_tv.R;

public class TaskBean {
    private int iconRes;
    private String takeName;
    private String goldNum;
    private String describe;
    private String checkedText, unCheckedText;
    private int type;
    private boolean isComplete;

    public static final int signIn = 0;//签到任务
    public static final int invitation = 1;//邀请用户注册
    public static final int watchMovie = 2;//观影满30分钟
    public static final int share = 3;//每日分享影片
    public static final int keepUrl = 4;//保存官网地址
    public static final int ad = 5;//点击广告

    public TaskBean(int type) {
        this.type = type;
        switch (type) {
            case signIn:
                iconRes = R.mipmap.take_image_signin_icon;
                takeName = "签到任务";
                checkedText = "已签到";
                unCheckedText = "去签到";
                break;
            case invitation:
                iconRes = R.mipmap.take_image_invitation_icon;
                takeName = "邀请用户注册";
                checkedText = "已邀请";
                unCheckedText = "去邀请";
                break;
            case watchMovie:
                iconRes = R.mipmap.take_image_watchmovie_icon;
                takeName = "观影满30分钟";
                checkedText = "已观看";
                unCheckedText = "去观看";
                break;
            case share:
                iconRes = R.mipmap.take_image_share_icon;
                takeName = "每日分享影片";
                checkedText = "已分享";
                unCheckedText = "去分享";
                break;
            case keepUrl:
                iconRes = R.mipmap.take_image_keepurl_icon;
                takeName = "保存官网地址";
                checkedText = "已保存";
                unCheckedText = "去保存";
                break;
            case ad:
                iconRes = R.mipmap.take_image_ad_icon;
                takeName = "点击广告";
                checkedText = "已点击";
                unCheckedText = "去点击";
                break;
        }
    }

    public int getIconRes() {
        return iconRes;
    }

    public String getTakeName() {
        return takeName;
    }

    public String getGoldNum() {
        return goldNum;
    }

    public String getDescribe() {
        return describe;
    }

    public String getCheckedText() {
        return checkedText;
    }

    public String getUnCheckedText() {
        return unCheckedText;
    }

    public int getType() {
        return type;
    }

    public boolean isComplete() {
        return isComplete;
    }
}
