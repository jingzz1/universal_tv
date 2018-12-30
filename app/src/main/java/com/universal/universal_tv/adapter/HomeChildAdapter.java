package com.universal.universal_tv.adapter;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.universal.universal_tv.R;
import com.universal.universal_tv.bean.HomeChildAdvertBean;
import com.universal.universal_tv.bean.HomeChildBean;
import com.universal.universal_tv.bean.HomeChildPosterBean;

import java.util.List;

public class HomeChildAdapter extends BaseMultiItemQuickAdapter<HomeChildBean, BaseViewHolder> {

    public HomeChildAdapter() {
        super(null);
        addItemType(HomeChildBean.advert, R.layout.home_child_item_advert);
        addItemType(HomeChildBean.posterIn6, R.layout.home_child_item_in6);
        addItemType(HomeChildBean.posterIn3, R.layout.home_child_item_in3);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeChildBean item) {
        switch (helper.getItemViewType()) {
            case HomeChildBean.advert:
                initAdvertView(helper, item);
                break;
            default:
                initPosterView(helper, item);
        }
    }

    private void initPosterView(BaseViewHolder helper, HomeChildBean item) {
        HomeChildPosterBean bean = (HomeChildPosterBean) item;
        View color = helper.getView(R.id.home_child_item_color);
        helper.setText(R.id.home_child_item_title, bean.title);
        List<HomeChildPosterBean.PosterBean> list = bean.getList();
        if (list.size() > 0) {
            String image = list.get(0).image;
            initColor(color, image);
        }

        RecyclerView recyclerView = helper.getView(R.id.home_child_item_recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(mContext, 3));
        HomeChildItemAdapter adapter = new HomeChildItemAdapter(((HomeChildPosterBean) item).getList());
        recyclerView.setAdapter(adapter);
    }

    /**
     * 获取颜色
     *
     * @param view
     * @param image
     */
    private void initColor(View view, String image) {
        Glide.with(mContext).asBitmap().load(image).into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(@NonNull Bitmap bitmap, @Nullable Transition<? super Bitmap> transition) {

                Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
                    @Override
                    public void onGenerated(@Nullable Palette palette) {
                        int color = ContextCompat.getColor(mContext, R.color.colorAccent);
                        Palette.Swatch swatch = palette.getDarkMutedSwatch();
                        if (swatch == null) {
                            swatch = palette.getVibrantSwatch();
                            if (swatch == null)
                                swatch = palette.getLightMutedSwatch();
                        }

                        if (swatch != null)
                            color = swatch.getRgb();

                        initColor2(view, color);
                    }
                });

            }
        });
    }

    /**
     * 获取颜色，生成该颜色的圆形draw
     *
     * @param view
     * @param color
     */
    private void initColor2(View view, int color) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.OVAL);
        drawable.setColor(color);
        Drawable mutate = drawable.mutate();
        view.setBackground(drawable.mutate());
    }

    private void initAdvertView(BaseViewHolder helper, HomeChildBean item) {
        HomeChildAdvertBean bean = (HomeChildAdvertBean) item;
        ImageView imageView = helper.getView(R.id.home_child_item_advert_image);
        Glide.with(mContext).load(bean.getImage()).into(imageView);
    }

    public static class HomeChildItemAdapter extends BaseQuickAdapter<HomeChildPosterBean.PosterBean, BaseViewHolder> {

        public HomeChildItemAdapter(@Nullable List<HomeChildPosterBean.PosterBean> data) {
            super(R.layout.home_child_poster_item, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, HomeChildPosterBean.PosterBean item) {
            View view = helper.itemView;
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            layoutParams.width = ScreenUtils.getScreenWidth()/3;
            view.setLayoutParams(layoutParams);
            ImageView imageView = helper.getView(R.id.home_child_poster_item_image);
            Glide.with(mContext).load(item.image).apply(new RequestOptions().error(R.mipmap.erron)).into(imageView);
            helper.setText(R.id.home_child_poster_item_title, item.title)
                    .setText(R.id.home_child_poster_item_depict, item.depict);
            int position = helper.getLayoutPosition() % 3;
            ViewGroup.LayoutParams params = imageView.getLayoutParams();
            if (params instanceof LinearLayout.LayoutParams) {
                LinearLayout.LayoutParams params1 = (LinearLayout.LayoutParams) params;
                switch (position) {
                    case 0:
                        params1.gravity = Gravity.LEFT;
                        break;
                    case 1:
                        params1.gravity = Gravity.CENTER_HORIZONTAL;
                        break;
                    case 2:
                        params1.gravity = Gravity.RIGHT;
                        break;
                }
                imageView.setLayoutParams(params1);
            }


        }

        @Override
        public int getItemCount() {
            int size = getData().size();
            return size > 6 ? 6 : size;
        }
    }

}
