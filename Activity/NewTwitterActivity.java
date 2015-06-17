package com.example.saber.bookchat;

import android.app.Activity;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;

/**
 * Created by Saber on 2015/6/13.
 */

public class NewTwitterActivity extends Activity {

    Button tw;
    int [] imageIds = new int[]
            {
                    R.mipmap.cover1, R.mipmap.cover2_,R.mipmap.cover3,R.mipmap.cover4,R.mipmap.cover5
            };

    Gallery gallery;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_new_message);
        initComponent();
        initOCL();

    }

    void initOCL()
    {
        tw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                NewTwitterActivity.this.setResult(XListViewActivity.RESULT_FOR_NEW_TWITTER);
                NewTwitterActivity.this.finish();
            }
        });
    }

    void initComponent()
    {
        tw = (Button) findViewById(R.id.button);
        gallery = (Gallery) findViewById(R.id.gallery);
        BaseAdapter adapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return imageIds.length;
            }

            @Override
            public Object getItem(int position) {
                return position;
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                ImageView iv = new ImageView(NewTwitterActivity.this);
                iv.setImageResource(imageIds[position]);
                iv.setScaleType(ImageView.ScaleType.FIT_XY);
                iv.setLayoutParams(new Gallery.LayoutParams(75, 100));
                //TypedArray ta = obtainStyledAttributes(R.styleable.);

                return iv;
            }
        };

    }
}
