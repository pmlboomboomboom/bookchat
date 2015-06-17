package com.example.saber.bookchat.ViewComponent;

import android.content.Context;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Saber on 2015/4/21.
 */
public class standrandUserNameInputEditText extends EditText implements View.OnFocusChangeListener, TextWatcher {

    public standrandUserNameInputEditText(Context context) {
        super(context);
    }

    public standrandUserNameInputEditText(Context context, AttributeSet attrs) {

        this(context, attrs, android.R.attr.editTextStyle);
    }

    public standrandUserNameInputEditText (Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setFilters(new InputFilter[]{new MyInputFilter()});
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int count, int after) {

    }

    public class MyInputFilter implements InputFilter {

        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {

            Log.w("Filter", "src:" + source + ";start:" + start + ";end:" + end);
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
