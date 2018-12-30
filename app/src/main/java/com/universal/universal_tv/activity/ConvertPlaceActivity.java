package com.universal.universal_tv.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.universal.universal_tv.R;
import com.universal.universal_tv.base.BaseActivity;
import com.universal.universal_tv.bean.ConvertPlaceBean;
import com.universal.universal_tv.bean.ConvertPlaceChangeBean;
import com.universal.universal_tv.bean.ConvertPlaceTitleBean;
import com.universal.universal_tv.bean.ConvertPlaceUnChangeBean;

import java.util.ArrayList;
import java.util.List;

public class ConvertPlaceActivity extends BaseActivity {

    private TextView tv_goldAmount;
    private RecyclerView recyclerView;
    private ConvertPlaceAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convert_place);

        initToolbar("兑换中心");
        initView();

    }

    private void initView() {
        tv_goldAmount = findViewById(R.id.convert_place_goldAmount);
        initRecyclerView();
    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.convert_place_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ConvertPlaceAdapter();
        recyclerView.setAdapter(adapter);
        List<ConvertPlaceBean> list = new ArrayList<>();
        list.add(new ConvertPlaceTitleBean());
        list.add(new ConvertPlaceChangeBean());
        list.add(new ConvertPlaceChangeBean());
        list.add(new ConvertPlaceChangeBean());
        list.add(new ConvertPlaceTitleBean());
        list.add(new ConvertPlaceUnChangeBean());
        list.add(new ConvertPlaceUnChangeBean());
        list.add(new ConvertPlaceUnChangeBean());
        adapter.setNewData(list);
    }

    public static class ConvertPlaceAdapter extends BaseMultiItemQuickAdapter<ConvertPlaceBean,BaseViewHolder>{

        public ConvertPlaceAdapter() {
            super(null);
            addItemType(ConvertPlaceBean.changeType,R.layout.convert_place_change);
            addItemType(ConvertPlaceBean.unchangeType,R.layout.convert_place_unchange);
            addItemType(ConvertPlaceBean.titleType,R.layout.convert_place_title);
        }

        @Override
        protected void convert(BaseViewHolder helper, ConvertPlaceBean item) {
            switch (helper.getItemViewType()){
                case ConvertPlaceBean.titleType:
                    helper.setText(R.id.convert_place_item_title,((ConvertPlaceTitleBean)item).getTitle());
                    ;
                    break;
            }
        }
    }

}
