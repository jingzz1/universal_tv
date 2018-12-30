package com.universal.universal_tv.base;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.base.activity.BasicActivity;
import com.base.base.slidingshut.SlidingShutActivity;
import com.blankj.utilcode.util.StringUtils;
import com.universal.universal_tv.R;

public class BaseActivity extends SlidingShutActivity {
    private ImageView iv_back;
    private TextView tv_title;
    private TextView tv_rightText;


    protected void initToolbar(String title){
        initToolbar(title,true,null,null,null);
    }

    protected void initToolbar(String title,boolean backIconIsVisible){
        initToolbar(title,backIconIsVisible,null,null,null);
    }

    protected void initToolbar(String title, View.OnClickListener backListener){
        initToolbar(title,true,backListener,null,null);
    }

    protected void initToolbar(String title, String rightText, View.OnClickListener rightTextlistener){
        initToolbar(title,true,null,rightText,rightTextlistener);
    }
    protected void initToolbar(String title,boolean backIconIsVisible,String rightText, View.OnClickListener rightTextlistener){
        initToolbar(title,backIconIsVisible,null,rightText,rightTextlistener);
    }

    protected void initToolbar(@NonNull String title, boolean backIconIsVisible, View.OnClickListener backListener, String rightText, View.OnClickListener rightTextlistener) {
        iv_back = findViewById(R.id.toolbar_back);
        iv_back.setVisibility(backIconIsVisible ? View.VISIBLE : View.GONE);
        if (backListener == null)
            iv_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        else
            iv_back.setOnClickListener(backListener);

        tv_title = findViewById(R.id.toolbar_title);
        tv_title.setText(title);

        tv_rightText = findViewById(R.id.toolbar_rightText);
        if (StringUtils.isEmpty(rightText))
            tv_rightText.setVisibility(View.GONE);
        else {
            tv_rightText.setVisibility(View.VISIBLE);
            tv_rightText.setText(rightText);
            if (rightTextlistener != null)
                tv_rightText.setOnClickListener(rightTextlistener);
        }

    }
}
