package com.example.saber.bookchat.ViewComponent;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.saber.bookchat.R;

/**
 * Created by Saber on 2015/4/21.
 */
public class clearInputEditText extends EditText implements View.OnFocusChangeListener, TextWatcher {

    private Drawable mClearDrawable;
    private boolean hasFoucs = false;


    public clearInputEditText(Context context) {
        super(context);
    }

    public clearInputEditText(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.editTextStyle);
    }

    public clearInputEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        mClearDrawable = getResources().getDrawable(R.mipmap.clear_ic);
        mClearDrawable.setBounds(0, 0, mClearDrawable.getIntrinsicWidth(), mClearDrawable.getIntrinsicHeight());

        setClearIconVisible(false);

        setOnFocusChangeListener(this);

        addTextChangedListener(this);
        setFilters(new InputFilter[]{new MyInputFilter()});

        }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (getCompoundDrawables()[2] != null) {
                boolean touchable = event.getX() > (getWidth() - getTotalPaddingRight()) && (event.getX() < ((getWidth() - getPaddingRight())));
                if (touchable) {
                    this.setText("");
                }
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        this.hasFoucs = hasFocus;
        if (hasFocus) {
            setClearIconVisible(getText().length() > 0);
        } else {
            setClearIconVisible(false);
        }
    }

    private void setClearIconVisible(boolean visible) {
        Drawable right = visible ? mClearDrawable : null;
        setCompoundDrawables(getCompoundDrawables()[0], getCompoundDrawables()[1], right, getCompoundDrawables()[3]);

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int count, int after) {
        if (hasFoucs) {
            setClearIconVisible(s.length() > 0);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }


    public class MyInputFilter implements InputFilter {

        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {

            Log.w("Filter", "src:"  + source + ";start:" + start + ";end:" + end);
            Log.w("Filter", "dest:" + dest + ";dstart:" + dstart + ";dend:" + dend);

            if (start < end)
            {
                char c = source.charAt(0);
                Log.i("C", String.valueOf(c));

                if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z' ) || (c >= '0' && c <=  '9') )
                    return source;
                else
                    return dest.subSequence(dstart, dend);
            }

            return source;
        }
    }
}
