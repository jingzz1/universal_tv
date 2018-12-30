package com.universal.universal_tv.ui.dialog;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.ToastUtils;
import com.universal.universal_tv.R;

public class ShareBottomDialog extends BottomSheetDialogFragment implements View.OnClickListener {

    public static ShareBottomDialog newInstance() {
        ShareBottomDialog fragment = new ShareBottomDialog();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_share_bottom_dialog, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findViewById(R.id.ShareBottomDialog_cancel).setOnClickListener(this);
        findViewById(R.id.ShareBottomDialog_wechatMoments).setOnClickListener(this);
        findViewById(R.id.ShareBottomDialog_wechat).setOnClickListener(this);
        findViewById(R.id.ShareBottomDialog_qq).setOnClickListener(this);
        findViewById(R.id.ShareBottomDialog_qqZone).setOnClickListener(this);
        findViewById(R.id.ShareBottomDialog_weibo).setOnClickListener(this);
        findViewById(R.id.ShareBottomDialog_link).setOnClickListener(this);
    }

    protected <T extends View> T findViewById(int id) {
        return getView().findViewById(id);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ShareBottomDialog_cancel:
                dismiss();
                break;
            case R.id.ShareBottomDialog_wechatMoments:
                //分享到朋友圈
                break;
            case R.id.ShareBottomDialog_wechat:
                //分享到微信好友
                break;
            case R.id.ShareBottomDialog_qq:
                //分享到qq
                break;
            case R.id.ShareBottomDialog_qqZone:
                //分享到qq空间
                break;
            case R.id.ShareBottomDialog_weibo:
                //分享到微博
                break;
            case R.id.ShareBottomDialog_link:
                //复制链接
                break;
        }
    }
}
