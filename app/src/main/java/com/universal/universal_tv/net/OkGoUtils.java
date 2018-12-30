package com.universal.universal_tv.net;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.request.GetRequest;
import com.lzy.okgo.request.PostRequest;

/**
 * Created by Administrator on 2018/4/14.
 */

public class OkGoUtils {

    public static GetRequest<String> get(String url){
         return OkGo.<String>get(url);
    }

    public static PostRequest<String> post(String url){
        return OkGo.<String>post(url);
    }


    public static <T>GetRequest get2(String url){
         return OkGo.<BaseBean<T>>get(url);
    }

    public static <T>PostRequest post2(String url){
        return OkGo.<BaseBean<T>>post(url);
    }

}
