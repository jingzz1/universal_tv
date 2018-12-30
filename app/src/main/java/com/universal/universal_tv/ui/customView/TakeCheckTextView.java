package com.universal.universal_tv.ui.customView;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.CheckedTextView;

import com.universal.universal_tv.R;

public class TakeCheckTextView extends android.support.v7.widget.AppCompatCheckedTextView {

    private String checkedText,urCheckedText;

    public TakeCheckTextView(Context context) {
        this(context,null);
    }

    public TakeCheckTextView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TakeCheckTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        String text = getText().toString();
        if(TextUtils.isEmpty(text))
            text = "";
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.TakeCheckTextView);
        checkedText = array.getString(R.styleable.TakeCheckTextView_tct_checkedText);
        if(TextUtils.isEmpty(checkedText))
            checkedText = text;
        urCheckedText = array.getString(R.styleable.TakeCheckTextView_tct_urCheckedText);
        if(TextUtils.isEmpty(checkedText))
            checkedText = checkedText;
        array.recycle();
        setText(isChecked()?checkedText:urCheckedText);
    }

    @Override
    public void toggle() {
        super.toggle();
    }

    @Override
    public void setChecked(boolean checked) {
        super.setChecked(checked);
        setText(checked?checkedText:urCheckedText);
    }

    public void setCheckedText(String checkedText) {
        this.checkedText = checkedText;
        invalidate();
    }

    public void setUrCheckedText(String urCheckedText) {
        this.urCheckedText = urCheckedText;
        invalidate();
    }
}
