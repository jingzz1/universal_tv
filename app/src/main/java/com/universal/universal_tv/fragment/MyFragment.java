package com.universal.universal_tv.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.universal.universal_tv.R;
import com.universal.universal_tv.activity.ConvertPlaceActivity;
import com.universal.universal_tv.activity.MyMessageActivity;
import com.universal.universal_tv.activity.SettingActivity;
import com.universal.universal_tv.activity.WalletActivity;
import com.universal.universal_tv.base.BaseFragment;
import com.universal.universal_tv.bean.MyCacheRecordBaseBean;
import com.universal.universal_tv.bean.MyCacheRecordListBean;
import com.universal.universal_tv.bean.MyCacheRecordOnlyBean;
import com.universal.universal_tv.bean.PlayRecordBean;
import com.universal.universal_tv.viewModel.HomeViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页-我的fragment
 */
public class MyFragment extends BaseFragment implements View.OnClickListener {

    private HomeViewModel viewModel;
    private RecyclerView rv_playRecord;//播放记录
    private MyPlayRecordAdapter playRecordAdapter;
    private RecyclerView rv_cacheRecord;//缓存记录
    private MyCacheRecordAdapter cacheRecordAdapter;

    public static MyFragment newInstance() {
        MyFragment fragment = new MyFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    protected void initStart(View view, @Nullable Bundle savedInstanceState) {
        initViewModel();
        initView();
    }

    private void initView() {
        findViewById(R.id.my_layout_invite).setOnClickListener(this);
        findViewById(R.id.my_layout_convertPlace).setOnClickListener(this);
        findViewById(R.id.my_layout_wallet).setOnClickListener(this);
        findViewById(R.id.my_image_messgae).setOnClickListener(this);
        findViewById(R.id.my_image_setting).setOnClickListener(this);
        initRecyclerView();
    }

    private void initRecyclerView() {
        //播放记录
        rv_playRecord = findViewById(R.id.my_recyclerView_playRecord);
        rv_playRecord.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        playRecordAdapter = new MyPlayRecordAdapter();
        rv_playRecord.setAdapter(playRecordAdapter);
        List<PlayRecordBean> list = new ArrayList<>();
        list.add(new PlayRecordBean());
        list.addAll(list);
        list.addAll(list);
        list.addAll(list);
        list.addAll(list);
        playRecordAdapter.setNewData(list);

        //离线缓存
        rv_cacheRecord = findViewById(R.id.my_recyclerView_cacheRecord);
        rv_cacheRecord.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        cacheRecordAdapter = new MyCacheRecordAdapter();
        rv_cacheRecord.setAdapter(cacheRecordAdapter);
        List<MyCacheRecordBaseBean> list1 = new ArrayList<>();
        list1.add(new MyCacheRecordListBean());
        list1.add(new MyCacheRecordOnlyBean());
        list1.add(new MyCacheRecordOnlyBean());
        list1.addAll(list1);
        cacheRecordAdapter.setNewData(list1);
    }

    private void initViewModel() {
        viewModel = ViewModelProviders.of(getActivity()).get(HomeViewModel.class);
    }

    @Override
    protected void loadData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_my;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.my_layout_invite:
                viewModel.showFragment(1);
                break;
            case R.id.my_layout_wallet:
                startActivity(WalletActivity.class);
                break;
            case R.id.my_layout_convertPlace:
                startActivity(ConvertPlaceActivity.class);
                break;
            case R.id.my_image_messgae:
                startActivity(MyMessageActivity.class);
                break;
            case R.id.my_image_setting:
                startActivity(SettingActivity.class);
                break;
        }
    }

    public static class MyPlayRecordAdapter extends BaseQuickAdapter<PlayRecordBean,BaseViewHolder>{

        public MyPlayRecordAdapter() {
            super(R.layout.my_play_record_item);
        }

        @Override
        protected void convert(BaseViewHolder helper, PlayRecordBean item) {
            ImageView imageView = helper.getView(R.id.my_play_record_item_image);
            Glide.with(mContext).load(item.getUrl()).apply(new RequestOptions().error(R.mipmap.erron)).into(imageView);
            helper.setText(R.id.my_play_record_item_title,item.getTitle());
        }
    }

    public static class MyCacheRecordAdapter extends BaseMultiItemQuickAdapter<MyCacheRecordBaseBean,BaseViewHolder>{

        public MyCacheRecordAdapter() {
            super(null);
            addItemType(MyCacheRecordBaseBean.onlyType,R.layout.my_cache_record_only_item);
            addItemType(MyCacheRecordBaseBean.listType,R.layout.my_cache_record_list_item);
        }

        @Override
        protected void convert(BaseViewHolder helper, MyCacheRecordBaseBean item) {
            ImageView imageView = helper.getView(R.id.my_cache_record_item_image);
            Glide.with(mContext).load(item.getUrl())
                    .apply(new RequestOptions().error(R.mipmap.erron))
                    .into(imageView);
            helper.setText(R.id.my_cache_record_item_title,item.getTitle());
            if(item.getItemType() == MyCacheRecordBaseBean.listType){
                MyCacheRecordListBean bean = (MyCacheRecordListBean)item;
                helper.setText(R.id.my_cache_record_item_amount,bean.getCaches().size()+"");
            }
        }
    }

}
