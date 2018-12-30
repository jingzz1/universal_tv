package com.universal.universal_tv.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.universal.universal_tv.R;
import com.universal.universal_tv.activity.ConvertPlaceActivity;
import com.universal.universal_tv.activity.MyInvitationActivity;
import com.universal.universal_tv.base.BaseFragment;
import com.universal.universal_tv.bean.TaskBean;
import com.universal.universal_tv.ui.customView.TakeCheckTextView;

import java.util.ArrayList;
import java.util.List;

/**
 * 任务frgament
 */
public class TaskFragment extends BaseFragment {

    private RecyclerView recyclerView;
    private TaskAdapter adapter;

    public static TaskFragment newInstance() {
        TaskFragment fragment = new TaskFragment();
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
        initToolbar("任务中心", false);
        findViewById(R.id.task_myInvite).setOnClickListener(v -> startActivity(MyInvitationActivity.class));
        findViewById(R.id.task_convertPlace).setOnClickListener(v -> startActivity(ConvertPlaceActivity.class));
        initView();
    }

    private void initView() {
        recyclerView = findViewById(R.id.task_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new TaskAdapter();
        recyclerView.setAdapter(adapter);
        List<TaskBean> list = new ArrayList<>();
        list.add(new TaskBean(0));
        list.add(new TaskBean(1));
        list.add(new TaskBean(2));
        list.add(new TaskBean(3));
        list.add(new TaskBean(4));
        list.add(new TaskBean(5));
        adapter.setNewData(list);
    }

    @Override
    protected void loadData() {


    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_task;
    }

    public static class TaskAdapter extends BaseQuickAdapter<TaskBean,BaseViewHolder>{

        public TaskAdapter() {
            super(R.layout.task_item);
        }

        @Override
        protected void convert(BaseViewHolder helper, TaskBean item) {
            helper.setImageResource(R.id.take_item_image,item.getIconRes())
                    .setText(R.id.take_item_takeName,item.getTakeName());

            TakeCheckTextView checkTextView = helper.getView(R.id.take_text_checked);
            checkTextView.setCheckedText(item.getCheckedText());
            checkTextView.setUrCheckedText(item.getUnCheckedText());
            checkTextView.setChecked(item.isComplete());

        }
    }

}
