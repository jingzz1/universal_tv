package com.base.base.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.base.mvp.BasePresenter;
import com.base.mvp.MVPComposite;


public class MVPActivity extends RxJavaActivity {
    protected MVPComposite mvpComposite;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mvpComposite = new MVPComposite();
    }

    protected void addPresenter(BasePresenter presenter){
        mvpComposite.addPresenter(presenter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mvpComposite.clear();
    }
}
