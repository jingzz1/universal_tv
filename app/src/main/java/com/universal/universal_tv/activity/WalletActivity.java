package com.universal.universal_tv.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.universal.universal_tv.R;
import com.universal.universal_tv.base.BaseActivity;
import com.universal.universal_tv.bean.WalletIncomeBean;
import com.universal.universal_tv.utils.MyAppUtils;

import java.util.ArrayList;
import java.util.List;

public class WalletActivity extends BaseActivity {

    private TextView tv_amount;
    private RecyclerView recyclerView;
    private WalletIncomeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);
        initToolbar("金币记录");

        initView();
    }

    private void initView() {
        findViewById(R.id.wallet_text_toTask).setOnClickListener(v -> {
            MyAppUtils.jumpHomeActivity(this,2);
        });

        tv_amount = findViewById(R.id.wallet_text_amount);
        initRecyclerView();
    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.wallet_recyclerView_incomelist);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new WalletIncomeAdapter();
        recyclerView.setAdapter(adapter);
        List<WalletIncomeBean> beans = new ArrayList<>();
        beans.add(new WalletIncomeBean());
        beans.addAll(beans);
        beans.addAll(beans);
        beans.addAll(beans);
        beans.addAll(beans);
        beans.addAll(beans);
        beans.addAll(beans);
        beans.addAll(beans);
        adapter.setNewData(beans);
    }

    public static class WalletIncomeAdapter extends BaseQuickAdapter<WalletIncomeBean,BaseViewHolder>{

        public WalletIncomeAdapter() {
            super(R.layout.wallet_income_item);
        }

        @Override
        protected void convert(BaseViewHolder helper, WalletIncomeBean item) {

        }
    }
}
