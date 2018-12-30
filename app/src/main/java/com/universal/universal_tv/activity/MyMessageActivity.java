package com.universal.universal_tv.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.universal.universal_tv.R;
import com.universal.universal_tv.base.BaseActivity;

public class MyMessageActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_message);
        initToolbar("我的消息");
        findViewById(R.id.myMessage_systemMessage).setOnClickListener(this);
        findViewById(R.id.myMessage_commentMessage).setOnClickListener(this);
        findViewById(R.id.myMessage_feedback).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Bundle b = new Bundle();
        String title = null;
        switch (v.getId()) {
            case R.id.myMessage_systemMessage:
                title = "系统消息";
                break;
            case R.id.myMessage_commentMessage:
                title = "评论消息";
                break;
            case R.id.myMessage_feedback:
                title = "客服反馈";
                break;
        }
        b.putString("title",title);
        startActivity(MessageDetailsActivity.class,b);
    }
}
