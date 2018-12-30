package com.universal.universal_tv.net;

import android.content.Context;
import android.util.Log;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.lzy.okgo.exception.HttpException;
import com.lzy.okgo.exception.StorageException;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2018/4/14.
 */

public abstract class BeanCallBack<T> extends DialogCallBack<T> {

    public BeanCallBack() {
        super();
    }

    public BeanCallBack( Context context) {
        super(true, context);
    }

    @Override
    public T convertResponse(okhttp3.Response response) throws Throwable {
        ResponseBody body = response.body();
        if(body == null)
            return null;

        T data = null;
        Gson gson = new Gson();
        String json = body.string();
        Type superclass = getClass().getGenericSuperclass();
        Type type = ((ParameterizedType)superclass).getActualTypeArguments()[0];

        data = gson.fromJson(json,type);

        LogUtils.e("type-->"+type);
        LogUtils.e("data-->"+data);
        response.close();
        return data;
    }


    @Override
    public void onSuccess(Response<T> response) {
       if(response == null){
           success(null);
           return;
       }
        T body = response.body();
        success(body);
    }

    public abstract void success(T bean);

    @Override
    public void onStart(Request<T, ? extends Request> request) {
        super.onStart(request);

        LogUtils.e("onStart");
    }

    @Override
    public void onFinish() {
        super.onFinish();
        LogUtils.e("onFinish");
    }

    @Override
    public void onError(Response<T> response) {
        super.onError(response);
        Throwable exception = response.getException();
        if(exception != null)
            exception.printStackTrace();

        if(exception instanceof UnknownHostException){
            //网络连接失败
            ToastUtils.showLong("网络连接失败");
            Log.e("xinka:","UnknownHostException-->"+"网络连接失败-->"+exception.getMessage());
        }else if(exception instanceof SocketTimeoutException){
            //网络请求超时
            ToastUtils.showLong("网络请求超时");
            Log.e("xinka:","SocketTimeoutException-->"+"网络请求超时-->"+exception.getMessage());
        }else if(exception instanceof HttpException){
            //服务器响应码404和500，
            ToastUtils.showLong("服务器未响应");
            Log.e("xinka:","HttpException-->"+"服务器响应码404和500-->"+exception.getMessage());
        }else if(exception instanceof StorageException){
            //sd卡不存在或者没有权限
            ToastUtils.showLong("sd卡不存在或者没有读写权限");
            Log.e("xinka:","StorageException-->"+"sd卡不存在或者没有读写权限-->"+exception.getMessage());
        }else {
            String message = exception.getMessage();
            if(!StringUtils.isEmpty(message)){
                ToastUtils.showLong(message);
                LogUtils.e(message);
                System.out.println(message);
            }
        }
    }
}
