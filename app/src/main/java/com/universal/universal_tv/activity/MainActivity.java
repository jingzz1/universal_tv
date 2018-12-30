package com.universal.universal_tv.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.universal.universal_tv.R;
import com.universal.universal_tv.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startActivity(HomeActivity.class);
    }
}
