package com.universal.universal_tv.net;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/4/14.
 */

public class BaseBean<T> implements Serializable{

    /**
     * code : string
     * msg : string
     * obj : {}
     * success : true
     */

    public String code;
    public String msg;
    public T obj;
    public boolean success;
    public String signId;


}
