package com.universal.universal_tv.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.universal.universal_tv.R;
import com.universal.universal_tv.base.BaseActivity;
import com.universal.universal_tv.bean.MessageDetailsBean;

import java.util.ArrayList;
import java.util.List;

public class MessageDetailsActivity extends BaseActivity {

    String title;
    private RecyclerView recyclerView;
    private MessageDetailsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_details);

        Bundle bundle = getBundle();
        if(bundle != null)
           title = bundle.getString("title");
        if(TextUtils.isEmpty(title)){
            showToast("数据错误");
            onBackPressed();
        }
        initToolbar(title);
        initView();
    }

    private void initView() {
        recyclerView = findViewById(R.id.messageDetails_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MessageDetailsAdapter();
        recyclerView.setAdapter(adapter);
        List<MessageDetailsBean> list = new ArrayList<>();
        list.add(new MessageDetailsBean());
        list.add(new MessageDetailsBean());
        list.addAll(list);
        list.addAll(list);
        list.addAll(list);
        adapter.setNewData(list);
    }

    public static class MessageDetailsAdapter extends BaseQuickAdapter<MessageDetailsBean,BaseViewHolder>{

        public MessageDetailsAdapter() {
            super(R.layout.message_details_item);
        }

        @Override
        protected void convert(BaseViewHolder helper, MessageDetailsBean item) {
            helper.setText(R.id.message_details_item_title,item.getTitle())
                    .setText(R.id.message_details_item_context,item.getComtext())
                    .setText(R.id.message_details_item_time,item.getTime());
        }
    }

}
