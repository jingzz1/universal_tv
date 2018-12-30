package com.base.base.activity;

import android.app.AppOpsManager;
import android.app.NotificationManager;
import android.content.pm.ApplicationInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.base.R;
import com.base.bean.AlerterBean;
import com.tapadoo.alerter.Alert;
import com.tapadoo.alerter.Alerter;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by Administrator on 2018/7/10.
 * 提示activity，封装了Toase Snackbar等提示类
 */

public class PromptActivity extends RxJavaActivity {

    private PublishSubject<AlerterBean> publishSubject;
    private Alert alert;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rxJava();
    }

    public void showToast(String text) {
        showToast(text, Toast.LENGTH_SHORT);
    }

    public void showLongToast(String text) {
        showToast(text, Toast.LENGTH_LONG);
    }

    public void showToast(String text, int duration) {
        if (TextUtils.isEmpty(text))
            return;
        if(isNotificationEnabled())
            Toast.makeText(this, text, duration).show();
        else
            showAlerter(text);
    }

    public void showSnckbar(String text) {
        showSnckbar(text, null, null);
    }

    public void showSnckbar(String text, String actionText, View.OnClickListener listener) {
        showSnckbar(text, getColor1(android.R.color.white), getColor1(R.color.colorPrimary),
                actionText, listener, getColor1(android.R.color.white));
    }

    public void showSnckbar(String text, int textColor, int backColor
            , String actionText, View.OnClickListener listener, int actionTextColor) {

        if (TextUtils.isEmpty(text))
            return;

        Snackbar snackbar = Snackbar.make(getWindow().getDecorView(), text, Snackbar.LENGTH_SHORT);
        if (!TextUtils.isEmpty(actionText)) {

            snackbar.setAction(actionText, listener);
            snackbar.setActionTextColor(actionTextColor);
        }
        View view = snackbar.getView();
        view.setBackgroundColor(backColor);
        ((TextView) view.findViewById(R.id.snackbar_text)).setTextColor(textColor);
        snackbar.show();

    }

    public void showAlerter(String text) {
        showAlerter("提示", text);
    }


    public void showAlerter(String title, String text) {
        if (TextUtils.isEmpty(text))
            return;

        publishSubject.onNext(new AlerterBean(title, text));
    }

    private void rxJava() {
        publishSubject = PublishSubject.create();
        Disposable subscribe = publishSubject.throttleFirst(500, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<AlerterBean>() {
                    @Override
                    public void accept(AlerterBean alerterBean) throws Exception {
                        String text = alerterBean.text;
                        String title = alerterBean.title;

                        alert = Alerter.create(PromptActivity.this).setTitle(TextUtils.isEmpty(title) ? "提示" : title)
                                .setText(text)
                                .setDuration(text.length() / 4 * 1000)
                                .setBackgroundColorRes(R.color.colorPrimary)
                                .setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        alert.hide();
                                    }
                                })
                                .show();
                    }
                });
        addDisposable(subscribe);
    }


    public int getColor1(@ColorRes int id) {
        return ContextCompat.getColor(this, id);
    }

    //判断是否拥有通知使用权
    protected boolean notificationListenerEnable() {
        boolean enable = false;
        String packageName = getPackageName();
        String flat = Settings.Secure.getString(getContentResolver(), "enabled_notification_listeners");
        if (flat != null) {
            enable = flat.contains(packageName);
        }
        return enable;
    }

    protected boolean isNotificationEnabled(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return ((NotificationManager) getSystemService(NOTIFICATION_SERVICE)).areNotificationsEnabled();
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            AppOpsManager appOps = (AppOpsManager) getSystemService(APP_OPS_SERVICE);
            ApplicationInfo appInfo = getApplicationInfo();
            String pkg = getApplicationContext().getPackageName();
            int uid = appInfo.uid;

            try {
                Class<?> appOpsClass = Class.forName(AppOpsManager.class.getName());
                Method checkOpNoThrowMethod = appOpsClass.getMethod("checkOpNoThrow", Integer.TYPE, Integer.TYPE, String.class);
                Field opPostNotificationValue = appOpsClass.getDeclaredField("OP_POST_NOTIFICATION");
                int value = (Integer) opPostNotificationValue.get(Integer.class);
                return (Integer) checkOpNoThrowMethod.invoke(appOps, value, uid, pkg) == 0;
            } catch (NoSuchMethodException | NoSuchFieldException | InvocationTargetException | IllegalAccessException | RuntimeException | ClassNotFoundException ignored) {
                return true;
            }
        } else {
            return true;
        }
    }

}
