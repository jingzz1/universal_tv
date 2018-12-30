package com.universal.universal_tv.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Handler;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.universal.universal_tv.R;
import com.universal.universal_tv.base.BaseActivity;
import com.universal.universal_tv.fragment.HomeFragment;
import com.universal.universal_tv.fragment.MyFragment;
import com.universal.universal_tv.fragment.ShareFragment;
import com.universal.universal_tv.fragment.TaskFragment;
import com.universal.universal_tv.ui.dialog.NoviceAwardDialog;
import com.universal.universal_tv.ui.dialog.ShareBottomDialog;
import com.universal.universal_tv.viewModel.HomeViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends BaseActivity implements View.OnClickListener {

    private List<Fragment> fragments = new ArrayList<>();
    private HomeFragment homeFragment;
    private ShareFragment shareFragment;
    private TaskFragment taskFragment;
    private MyFragment myFragment;
    private int showIndex = -1;
    private LinearLayout ll_toHome,ll_toShare,ll_toTask,ll_toMy;
    private ImageView iv_toHome,iv_toShare,iv_toTask,iv_toMy;
    private TextView tv_toHome,tv_toShare, tv_toTask,tv_toMy;
    private ImageView[] imageViews = new ImageView[4];
    private TextView[] textViews = new TextView[4];
    private int[] selectIcons = {R.mipmap.home_home_icon_show,R.mipmap.home_share_icon_show,R.mipmap.home_task_icon_show,R.mipmap.home_my_icon_show};
    private int[] unSelectIcons = {R.mipmap.home_home_icon,R.mipmap.home_share_icon,R.mipmap.home_task_icon,R.mipmap.home_my_icon};

    private HomeViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        openSlideFinish(false);
        initViewModel();
        Bundle bundle = getBundle();
        if(bundle != null)
            showIndex = bundle.getInt("showIndex");


        initView();
        initFragment();
        showNoviceAwardDialog();
    }

    private void initViewModel() {
        viewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        viewModel.getShowIndex().observe(this,index -> {
            //处理从其他fragment传过来的跳转frament请求
            if(index>=0&&index < fragments.size())
            showFragment(index);
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //重启时保存当前显示页
        outState.putInt("showIndex",showIndex);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(savedInstanceState != null){
            //重启后显示重启前显示页
            showIndex = savedInstanceState.getInt("showIndex");
            int index = showIndex;
            showIndex = -1;
            showFragment(index);
        }
    }

    /**
     * 初始化控件
     */
    private void initView() {
        ll_toHome = findViewById(R.id.homeActivity_toHome);
        ll_toHome.setOnClickListener(this);
        iv_toHome = findViewById(R.id.homeActivity_imageView_toHome);
        tv_toHome = findViewById(R.id.homeActivity_textView_toHome);

        ll_toShare = findViewById(R.id.homeActivity_toShare);
        ll_toShare.setOnClickListener(this);
        iv_toShare = findViewById(R.id.homeActivity_imageView_toShare);
        tv_toShare = findViewById(R.id.homeActivity_textView_toShare);

        ll_toTask = findViewById(R.id.homeActivity_toTask);
        ll_toTask.setOnClickListener(this);
        iv_toTask = findViewById(R.id.homeActivity_imageView_toTask);
        tv_toTask = findViewById(R.id.homeActivity_textView_toTask);

        ll_toMy = findViewById(R.id.homeActivity_toMy);
        ll_toMy.setOnClickListener(this);
        iv_toMy = findViewById(R.id.homeActivity_imageView_toMy);
        tv_toMy = findViewById(R.id.homeActivity_textView_toMy);

        imageViews[0] = iv_toHome;
        imageViews[1] = iv_toShare;
        imageViews[2] = iv_toTask;
        imageViews[3] = iv_toMy;

        textViews[0] = tv_toHome;
        textViews[1] = tv_toShare;
        textViews[2] = tv_toTask;
        textViews[3] = tv_toMy;



    }



    private void initFragment() {
        fragments = getSupportFragmentManager().getFragments();
        if(fragments.size() <=0)
            addFragment();
    }

    /**
     * 添加fragment
     */
    private void addFragment() {
        fragments = new ArrayList<>();
        homeFragment = HomeFragment.newInstance();
        shareFragment = ShareFragment.newInstance();
        taskFragment = TaskFragment.newInstance();
        myFragment = MyFragment.newInstance();
        fragments.clear();
        fragments.add(homeFragment);
        fragments.add(shareFragment);
        fragments.add(taskFragment);
        fragments.add(myFragment);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        for (Fragment f : fragments)
            transaction.add(R.id.homeActivity_frameLayout,f);
        transaction.commit();
        //显示第一个fragment
        int index = showIndex;
        showIndex = -1;
        if(index == -1)
            index = 0;
        showFragment(index);
    }

    /**
     * 注册奖励提示
     * 判断是否已经注册，未注册弹出此窗口
     */
    private void showNoviceAwardDialog() {
        new Handler().postDelayed(() -> NoviceAwardDialog.newInstance().show(getSupportFragmentManager(),""),1000);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.homeActivity_toHome:
                showFragment(0);
                break;
            case R.id.homeActivity_toShare:
                showFragment(1);
                break;
            case R.id.homeActivity_toTask:
                showFragment(2);
                break;
            case R.id.homeActivity_toMy:
                showFragment(3);
                break;
        }
    }

    /**
     * 显示fragment
     * @param index
     */
    private void showFragment(int index) {
        if(showIndex == index)
            return;

        showIndex = index;
        if (fragments != null && fragments.size() > index) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.translate_start_in,R.anim.translate_start_out);
            for (int i = 0; i < 4; i++) {
                if (i == index) {
                    transaction.show(fragments.get(index));
                    imageViews[i].setImageResource(selectIcons[i]);
                    textViews[i].setTextColor(getColor1(R.color.colorAccent));
                } else{
                    transaction.hide(fragments.get(i));
                    imageViews[i].setImageResource(unSelectIcons[i]);
                    textViews[i].setTextColor(getColor1(R.color.grad_99));
                }
            }
            transaction.commit();
        }
    }

    public void showShareBottomDialog(){
        ShareBottomDialog.newInstance().show(getSupportFragmentManager(),"");
    }

}
