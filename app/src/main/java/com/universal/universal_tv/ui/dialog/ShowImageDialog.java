package com.universal.universal_tv.ui.dialog;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.base.base.BaseDialogFragment;
import com.blankj.utilcode.util.StringUtils;
import com.bumptech.glide.Glide;
import com.universal.universal_tv.R;
import com.universal.universal_tv.ui.customView.ZoomImageView;

public class ShowImageDialog extends BaseDialogFragment {
    // TODO: Rename parameter arguments, choose names that match
    private String path;
    private Bitmap bitmap;
    private ZoomImageView imageView;

    public static ShowImageDialog newInstance(String imagePaht, Bitmap bitmap) {
        ShowImageDialog fragment = new ShowImageDialog();
        Bundle args = new Bundle();
        if (bitmap != null)
            args.putParcelable("bitmap", bitmap);
        if (!StringUtils.isEmpty(imagePaht))
            args.putString("path", imagePaht);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Bundle args = getArguments();
            bitmap = args.getParcelable("bitmap");
            path = args.getString("path");
        }
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imageView = findViewById(R.id.showImage_imageView);
        if(bitmap != null)
            imageView.setImageBitmap(bitmap);
        else if(!StringUtils.isEmpty(path))
            Glide.with(getContext()).load(path).into(imageView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_show_image_dialog;
    }


}
