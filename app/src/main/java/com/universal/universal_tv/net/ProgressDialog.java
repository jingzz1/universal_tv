package com.universal.universal_tv.net;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.universal.universal_tv.R;

/**
 * Created by Administrator on 2018/4/23.
 */

public class ProgressDialog extends Dialog {

    private String massage;
    private TextView textView;

    public ProgressDialog(@NonNull Context context, String massage) {
        this(context,0);
        this.massage = massage;
    }

    public ProgressDialog(@NonNull Context context) {
        this(context,0);
    }

    public ProgressDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        initDialog(context);
    }

    private void initDialog(Context context) {
        LinearLayout ll = new LinearLayout(context);
        LinearLayout.LayoutParams params =new LinearLayout.LayoutParams( LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ll.setLayoutParams(params);
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.setGravity(Gravity.CENTER_HORIZONTAL);
        ll.setPadding(dp2px(0),dp2px(0),dp2px(0),dp2px(0));

        ProgressBar progressBar = new ProgressBar(context);
        ll.addView(progressBar);
        LinearLayout.LayoutParams params1 = (LinearLayout.LayoutParams) progressBar.getLayoutParams();
        params1.setMargins(0,dp2px(24),0,dp2px(24));
        progressBar.setLayoutParams(params1);
        progressBar.setIndeterminateDrawable(ContextCompat.getDrawable(context,R.drawable.progressdialog_bg));

        textView = new TextView(context);
        LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params2.setMargins(dp2px(24),0,dp2px(24),dp2px(12));
        textView.setLayoutParams(params2);
        textView.setTextColor(ContextCompat.getColor(getContext(),R.color.black));
        textView.setGravity(Gravity.CENTER);
        ll.addView(textView);
        setContentView(ll);
        setMessage(massage);

        getWindow().setBackgroundDrawable(ContextCompat.getDrawable(context,android.R.color.white));
//        getWindow().setWindowAnimations(R.style.animate_dialog);
    }

    public void setMessage(String massage) {
        this.massage = massage;
        if(textView == null)
            return;

        textView.setText(massage);
        if(StringUtils.isEmpty(massage)){
            if(textView.getVisibility() != View.GONE){
                textView.setVisibility(View.GONE);
            }
        }else {
            if(textView.getVisibility() != View.VISIBLE){
                textView.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void dismiss() {
        if(isShowing() )
        super.dismiss();
    }

    /**
     * dp转换成px
     */
    private int dp2px(float dpValue){
        float scale=getContext().getResources().getDisplayMetrics().density;
        return (int)(dpValue*scale+0.5f);
    }
}
