package com.universal.universal_tv.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.universal.universal_tv.R;
import com.universal.universal_tv.ui.customView.BannerViewPager;

import java.util.List;

public class HomeBannerAdapter extends BannerViewPager.BaseBannerAdapter{

    public HomeBannerAdapter(List<String> images) {
        super(images);
    }

    @Override
    protected View getView(ViewGroup container, int position, String image) {
        ImageView imageView = new ImageView(container.getContext());
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        imageView.setLayoutParams(params);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Glide.with(context).load(image).apply(new RequestOptions().error(R.mipmap.erron)).into(imageView);
        return imageView;
    }
}
