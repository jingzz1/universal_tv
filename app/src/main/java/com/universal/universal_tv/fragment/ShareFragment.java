package com.universal.universal_tv.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ImageUtils;
import com.jingzz.permissionutil.PermissionAdapter;
import com.jingzz.permissionutil.PermissionInit;
import com.jingzz.permissionutil.PermissionUtil;
import com.universal.universal_tv.BuildConfig;
import com.universal.universal_tv.R;
import com.universal.universal_tv.activity.HomeActivity;
import com.universal.universal_tv.base.BaseFragment;
import com.universal.universal_tv.net.ProgressDialog;
import com.universal.universal_tv.ui.dialog.ShowImageDialog;
import com.universal.universal_tv.utils.ImageConvertUtils;

import java.io.File;
import java.io.FileOutputStream;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 分享fragment
 */
public class ShareFragment extends BaseFragment implements View.OnClickListener {

    private ImageView iv_qrImage;
    private TextView tv_copy,tv_invitationCode,tv_inviteRegister,tv_shareToLocal;
    private HomeActivity activity;
    private ProgressDialog dialog;

    public static ShareFragment newInstance() {
        ShareFragment fragment = new ShareFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {}
    }

    @Override
    protected void initStart(View view, @Nullable Bundle savedInstanceState) {

        activity = (HomeActivity) getActivity();
        dialog = new ProgressDialog(getContext());

        iv_qrImage = findViewById(R.id.share_imageView_qrCode);
        tv_copy = findViewById(R.id.share_textView_copy);
        tv_copy.setOnClickListener(this);
        tv_invitationCode = findViewById(R.id.share_textView_invitationCode);
        tv_inviteRegister = findViewById(R.id.share_inviteRegister);
        tv_inviteRegister.setOnClickListener(this);
        tv_shareToLocal = findViewById(R.id.share_shareToLocal);
        tv_shareToLocal.setOnClickListener(this);

    }

    @Override
    protected void loadData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_share;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.share_textView_copy:
                //把邀请码复制到复制榜
                break;
            case R.id.share_inviteRegister:
                //显示分享弹窗
                activity.showShareBottomDialog();
                break;
            case R.id.share_shareToLocal:
                //截图

                screenshot();



                break;
        }
    }

    /**
     * 截图：获取读写权限
     */
    private void screenshot() {

        PermissionUtil.builder(getActivity())
                .addPermissions(PermissionInit.STORAGE)
                .setExplain("截图并保存在本地需要sd卡读写权限，如果没有此权限，无法正常截图")
                .execute(new PermissionAdapter(){
                    @Override
                    public void allAgree() {
                        screenshot2();
                    }
                });
    }

    /**
     * 截图：把view转换成bitmap,再把bitmap转换成file保存到本地
     */
    @SuppressLint("CheckResult")
    private void screenshot2() {
        dialog.dismiss();
        dialog.setMessage("图片保存中");
        dialog.show();
        Observable.create(new ObservableOnSubscribe<File>() {
            @Override
            public void subscribe(ObservableEmitter<File> emitter) throws Exception {
                Bitmap bitmap = ImageUtils.view2Bitmap(getView());
                String cacheFileNmame = System.currentTimeMillis() + (int) (Math.random() * 1000) + ".jpg";
                File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), cacheFileNmame);
                FileOutputStream fileOutStream = null;
                fileOutStream = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutStream); // 把位图输出到指定的文件中
                fileOutStream.flush();
                fileOutStream.close();

                emitter.onNext(file);
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<File>() {
                    @Override
                    public void accept(File file) throws Exception {
                        dialog.dismiss();
                        Uri uri;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            uri = FileProvider.getUriForFile(getContext(), BuildConfig.authorities, file);
                        } else {
                            uri = Uri.fromFile(file);
                        }
                        //把图片写入系统存储数据库，这样其他app才能查询到图片
                        getContext().sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri));
                        ShowImageDialog.newInstance(file.getAbsolutePath(),null).show(getChildFragmentManager(),"");
                        showAlerter("图片保存成功，保存位置："+file.getAbsolutePath());
                    }
                });
    }
}
