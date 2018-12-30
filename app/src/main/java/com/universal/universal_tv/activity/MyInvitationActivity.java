package com.universal.universal_tv.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.universal.universal_tv.R;
import com.universal.universal_tv.base.BaseActivity;
import com.universal.universal_tv.bean.MyInvitationBean;
import com.universal.universal_tv.utils.MyAppUtils;

import java.util.ArrayList;
import java.util.List;

public class MyInvitationActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private MyInvitationAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_invitation);

        initToolbar("我的邀请","邀请好友",v -> {
            //去分享界面
            MyAppUtils.jumpHomeActivity(this,1);
        });
        initView();
    }
    private void initView() {
        initRecyclerView();
    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.myInvitation_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyInvitationAdapter();
        recyclerView.setAdapter(adapter);


        List<MyInvitationBean> list = new ArrayList<>();
        list.add(new MyInvitationBean());
        list.addAll(list);
        list.addAll(list);
        list.addAll(list);
        list.addAll(list);
        list.addAll(list);
        adapter.setNewData(list);

    }

    public static class MyInvitationAdapter extends BaseQuickAdapter<MyInvitationBean,BaseViewHolder>{

        public MyInvitationAdapter() {
            super(R.layout.my_invitation_item);
        }

        @Override
        protected void convert(BaseViewHolder helper, MyInvitationBean item) {

        }
    }

}
