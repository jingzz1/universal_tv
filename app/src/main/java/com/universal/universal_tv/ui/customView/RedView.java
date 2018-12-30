package com.universal.universal_tv.ui.customView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;

public class RedView extends View {

    private Paint paint;

    public RedView(Context context) {
        this(context, null);
    }

    public RedView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RedView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
        paint.setColor(Color.parseColor("#d4000e"));
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int width = MeasureSpec.getSize(widthMeasureSpec);
        int heigh = MeasureSpec.getSize(heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heighMode = MeasureSpec.getMode(heightMeasureSpec);
        if (widthMode == MeasureSpec.EXACTLY || heighMode == MeasureSpec.EXACTLY) {
            if (widthMode == MeasureSpec.EXACTLY) {
                heigh = (int) ((float) (width - getPaddingLeft() - getPaddingRight())) + getPaddingTop() + getPaddingBottom();
            } else {
                width = (int) ((float) (heigh - getPaddingTop() - getPaddingBottom())) + getPaddingLeft() + getPaddingRight();
            }
        } else {
            if (width >= heigh) {
                heigh = (int) ((float) (width - getPaddingLeft() - getPaddingRight())) + getPaddingTop() + getPaddingBottom();
            } else {
                width = (int) ((float) (heigh - getPaddingTop() - getPaddingBottom())) + getPaddingLeft() + getPaddingRight();
            }

        }
        widthMeasureSpec = MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY);
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(heigh, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        RectF rectF = new RectF(getPaddingLeft(), getPaddingTop(), getWidth()-getPaddingRight(), getHeight()-getPaddingBottom());
        canvas.drawOval(rectF,paint);
    }
}
