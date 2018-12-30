package com.universal.universal_tv.ui.dialog;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.base.base.BaseDialogFragment;
import com.universal.universal_tv.R;


public class NoviceAwardDialog extends BaseDialogFragment {


    public static NoviceAwardDialog newInstance() {
        NoviceAwardDialog fragment = new NoviceAwardDialog();
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
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViewById(R.id.noviceAwardDialog_cancel).setOnClickListener(v -> dismiss());
        findViewById(R.id.noviceAwardDialog_toRegister).setOnClickListener(v -> {
            //此处需要添加跳转到注册页逻辑
            showToast("跳转到注册页");
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_novice_award_dialog;
    }


}
