package com.universal.universal_tv.net;

import android.content.Context;

import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.request.base.Request;

/**
 * Created by Administrator on 2018/4/17.
 */

public abstract class DialogCallBack<T> extends AbsCallback<T> {

    private ProgressDialog progressDialog;
    private boolean isShowDialog;
    private Context context;

    public DialogCallBack(){
        this(false,null);
    }

    public DialogCallBack(boolean isShowDialog ,Context context){
        this.isShowDialog = isShowDialog;
        this.context = context;
        initDialog();
    }

    //设置弹窗提示
    public void initDialog(){
        if(context == null)
            return;

        if(!isShowDialog)
            return;

        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("请求网络中……");
        progressDialog.setCanceledOnTouchOutside(false);
    }

    @Override
    public void onStart(Request<T, ? extends Request> request) {
        super.onStart(request);
        if(progressDialog != null && !progressDialog.isShowing()){
            progressDialog.show();
        }
    }

    @Override
    public void onFinish() {
        super.onFinish();
        if(progressDialog != null && progressDialog.isShowing()){
            progressDialog.dismiss();
        }
    }
}
