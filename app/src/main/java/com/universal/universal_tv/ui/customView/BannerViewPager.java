package com.universal.universal_tv.ui.customView;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.blankj.utilcode.util.LogUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.universal.universal_tv.R;
import com.universal.universal_tv.utils.MyAppUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by Administrator on 2018/5/5.
 * 无限循环轮播，简单操作setImages(),需要订制图片样式，自定义adapter继承BaseBannerAdapter
 */

public class BannerViewPager extends ViewPager {

    private List<String> images = new ArrayList<>();
    private BaseBannerAdapter adapter;

    public BannerViewPager(@NonNull Context context) {
        this(context, null);
    }

    public BannerViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public List<String> getImages() {
        return images;
    }


    public void setBanndrAdapter(@Nullable BaseBannerAdapter adapter) {
        setAdapter(adapter);
        this.adapter = adapter;
        images = adapter.getImages();
        int size = images.size();
        setCurrentItem(size == 0 ? 0 :  1000/size*size, false);
    }


    private long clickTime = 0;
    private float x = 0.0f;

    @Override
    public boolean onTouchEvent(MotionEvent ev) {


        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                clickTime = System.currentTimeMillis();
                x = ev.getX();
                break;
            case MotionEvent.ACTION_UP:
                if (Math.abs(ev.getX() - x) > 10)
                    break;

                if ((System.currentTimeMillis() - clickTime <= 200) && images.size() > 0) {
                    //触摸回调
                    int currentItem = getCurrentItem() % images.size();

                    for (OnClickItemListener onClickItemListener : clickItemListeners) {
                        onClickItemListener.onClickItemListener(currentItem);
                    }
                }
                break;
        }

        return super.onTouchEvent(ev);
    }

    private List<OnClickItemListener> clickItemListeners = new ArrayList<>();

    public void addOnClickItemListener(OnClickItemListener onClickItemListener) {
        if (onClickItemListener != null)
            clickItemListeners.add(onClickItemListener);
    }

    private Disposable subscribe;

    public void startScroll(){

        shopScroll();

        MyAppUtils.controlViewPagerSpeed(getContext(),this,500);
        subscribe = Observable.interval(3, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        setCurrentItem(getCurrentItem()+1);
                    }
                });
    }

    public void shopScroll(){
        if(subscribe != null)
            subscribe.dispose();
    }


    public interface OnClickItemListener {
        public void onClickItemListener(int position);
    }

    private static class BannerAdapter extends BaseBannerAdapter {

        public BannerAdapter(List<String> images) {
            super(images);
        }

        @Override
        protected View getView(ViewGroup container, int position, String image) {
            LogUtils.e("position-->" + position);
            ImageView view = new ImageView(container.getContext());
            Glide.with(context).load(image).apply(new RequestOptions().error(R.mipmap.erron)).into(view);
            return view;
        }
    }

    public static abstract class BaseBannerAdapter extends PagerAdapter {

        private List<String> images = new ArrayList<>();
        protected Context context;

        public BaseBannerAdapter(List<String> images) {
            if (images != null)
                this.images = images;
        }

        @Override
        public int getCount() {
            return images.size() == 0 ? 0 : Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            context = container.getContext();
            position = position % images.size();
            String image = images.get(position);
            View view = getView(container, position, image);
            container.addView(view);
            return view;
        }

        protected abstract View getView(ViewGroup container, int position, String image);


        public List<String> getImages() {
            return images;
        }

        public void notifyDataSetChanged(List<String> images) {
            if (images != null) {
                this.images = images;
                notifyDataSetChanged();
            }
        }
    }

}
