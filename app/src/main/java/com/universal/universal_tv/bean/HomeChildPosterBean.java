package com.universal.universal_tv.bean;

import java.util.ArrayList;
import java.util.List;

public class HomeChildPosterBean extends HomeChildBean {

    public List<PosterBean> list = new ArrayList<>();
    public String title = "微观电影百态人生";

    public HomeChildPosterBean() {
        list = new ArrayList<>();
        list.add(new PosterBean());
        list.add(new PosterBean());
    }

    public void setList(List<PosterBean> list) {

        this.list = list;
    }

    public List<PosterBean> getList() {
        return list;
    }

    public class PosterBean{
        public String image = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545998966148&di=5a1fb51b803e91f179d396fe92cea939&imgtype=0&src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fq_mini%2Cc_zoom%2Cw_640%2Fimages%2F20170718%2F17b90288211b445c98f68ef42a85a6f5.jpg";
        public String title = "标题标题标题标题标题标题标题标题标题";
        public String depict = "描述描述描述描述描述描述描述描述描述描述描述描述";
    }
    @Override
    public int getItemType() {
        int size = list.size();
        if(size > 3)
            return posterIn6;
        else
            return posterIn3;
    }
}
