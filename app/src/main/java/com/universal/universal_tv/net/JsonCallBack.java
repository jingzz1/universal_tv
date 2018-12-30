package com.universal.universal_tv.net;

import android.content.Context;
import android.util.Log;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.exception.HttpException;
import com.lzy.okgo.exception.StorageException;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

/**
 * Created by Administrator on 2018/4/14.
 */

public abstract class JsonCallBack extends StringCallback {

    public Context context;
    private ProgressDialog progressDialog;

    public JsonCallBack() {
    }

    public JsonCallBack(Context context) {
        this.context = context;
        initDialog();

    }

    @Override
    public void onSuccess(Response<String> response) {
        String json = response.body();
        JsonBean jsonBean = new JsonBean();
        try {
            JSONObject jsonObject = new JSONObject(json);
            if (jsonObject.has("code")) {
                String code = jsonObject.getString("code");
                jsonBean.setCode(code);
            }
            if (jsonObject.has("msg")) {
                String msg = jsonObject.getString("msg");
                jsonBean.setMsg(msg);
            }
            if (jsonObject.has("success")) {
                boolean success = jsonObject.getBoolean("success");
                jsonBean.setSuccess(success);
            }
            if(jsonObject.has("signId")){
                String signId = jsonObject.getString("signId");
                jsonBean.setSignId(signId);
            }
            if(jsonObject.has("obj")){
                String obj = jsonObject.getString("obj");
                jsonBean.setObj(obj);
            }


        } catch (JSONException e) {
            e.printStackTrace();
            jsonBean.setMsg(e.getMessage());
            jsonBean.setSuccess(false);
        }
            onSuccess(jsonBean);


    }


    public abstract void onSuccess(JsonBean jsonBean);

    //设置弹窗提示
    public void initDialog() {
        if (context == null)
            return;

        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("网络较验证中……");
        progressDialog.setCanceledOnTouchOutside(false);
    }

    @Override
    public void onStart(Request<String, ? extends Request> request) {
        super.onStart(request);
        if (progressDialog != null && !progressDialog.isShowing()) {
            progressDialog.show();
        }


    }


    @Override
    public void onFinish() {
        super.onFinish();
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void onError(Response<String> response) {
        Throwable exception = response.getException();
        if(exception != null)
            exception.printStackTrace();

        if(exception instanceof UnknownHostException){
            //网络连接失败
            ToastUtils.showLong("网络连接失败");
            Log.e("xinka:","UnknownHostException-->"+"网络连接失败-->");
        }else if(exception instanceof SocketTimeoutException){
            //网络请求超时
            ToastUtils.showLong("网络请求超时");
            Log.e("xinka:","SocketTimeoutException-->"+"网络请求超时-->");
        }else if(exception instanceof HttpException){
            //服务器响应码404和500，
            ToastUtils.showLong("服务器未响应");
            Log.e("xinka:","HttpException-->"+"服务器响应码404和500-->");
        }else if(exception instanceof StorageException){
            //sd卡不存在或者没有权限
            ToastUtils.showLong("sd卡不存在或者没有读写权限");
            Log.e("xinka:","StorageException-->"+"sd卡不存在或者没有读写权限-->");
        }else {
            String message = exception.getMessage();
            if(!StringUtils.isEmpty(message))
                ToastUtils.showLong(message);
        }
    }
}
