package com.universal.universal_tv.net;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/4/14.
 */

public class JsonBean implements java.io.Serializable {
    private String code;
    private String msg;
    private String obj;
    public String signId;
    public boolean success;
    private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    public void setCode(String code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setObj(String obj) {
        this.obj = obj;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getSignId() {
        return signId;
    }

    public void setSignId(String signId) {
        this.signId = signId;
    }

    public <T> T getObj(Class<T> clz){
        if(TextUtils.isEmpty(obj)){
            return null;
        }
        return gson.fromJson(obj,clz);
    }



    public String getMsg() {
        return msg;
    }

    public String getObj() {
        return obj;
    }

    public String getCode() {
        return code;
    }

    public boolean isSuccess() {
        return success;
    }

    public <T>T getObj(Type type){
        if(TextUtils.isEmpty(obj)){
            return null;
        }
        return gson.fromJson(obj,type);
    }

    public <T>List<T> getList(Class<T> clz){
        if (TextUtils.isEmpty(obj)){
            return null;
        }
        List<T> list = new ArrayList<T>();
        Type listType = type(List.class,clz);
        list = gson.fromJson(obj,listType);
        return list;
    }

    static ParameterizedType type(final Class raw, final Type... args) {
        return new ParameterizedType() {
            public Type getRawType() {
                return raw;
            }

            public Type[] getActualTypeArguments() {
                return args;
            }

            public Type getOwnerType() {
                return null;
            }
        };
    }

}
