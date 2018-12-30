package com.universal.universal_tv.base;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.Utils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cookie.CookieJarImpl;
import com.lzy.okgo.cookie.store.MemoryCookieStore;
import com.universal.universal_tv.BuildConfig;
import com.universal.universal_tv.net.LoggerInterceptor;

import java.net.Proxy;

import okhttp3.OkHttpClient;

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initUtils();
        initOkGo();
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    /**
     * 初始化工具类
     */
    private void initUtils() {
        Utils.init(this);
        LogUtils.getConfig().setLogSwitch(BuildConfig.DEBUG);
    }

    /**
     * 配置okgo
     */
    private void initOkGo() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .addInterceptor(new LoggerInterceptor("拯救地球", BuildConfig.DEBUG))
                .proxy(Proxy.NO_PROXY)
                .cookieJar(new CookieJarImpl(new MemoryCookieStore()));
        OkGo.getInstance().setOkHttpClient(builder.build()).init(this);
    }
}
