package com.example.saber.bookchat;

import java.util.ArrayList;
import java.util.HashMap;
import com.example.saber.bookchat.R;

import android.content.ComponentName;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import com.example.saber.bookchat.ViewComponent.myListView;
import com.example.saber.bookchat.ViewComponent.myListView.MyListViewListener;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.content.Context;

public class XListViewActivity extends Activity implements MyListViewListener {

	int [] imageIds = new int[]
			{
					R.mipmap.head1, R.mipmap.head2,R.mipmap.head3,R.mipmap.head4,R.mipmap.head5,
					R.mipmap.head6, R.mipmap.head7,R.mipmap.head8,R.mipmap.head9
			};

	int [] imageId2s = new int[]
			{
					R.mipmap.cover1, R.mipmap.cover2_,R.mipmap.cover3,R.mipmap.cover4,R.mipmap.cover5,
					R.mipmap.cover6, R.mipmap.cover7, R.mipmap.cover8, R.mipmap.cover9,R.mipmap.cover10
			};

	int [] userNames = new int[]
			{
					R.string.user, R.string.user2, R.string.user3, R.string.user4, R.string.user5, R.string.user6,
					R.string.user7, R.string.user8, R.string.user9
			};

	int [] userTimes = new int[]
			{
					R.string.time, R.string.time2, R.string.time3, R.string.time4, R.string.time5, R.string.time6,
					R.string.time7, R.string.time8, R.string.time9
			};

	int [] comment = new int[]
			{
					R.string.content, R.string.content1,  R.string.content2,  R.string.content3, R.string.content4,
					R.string.content5, R.string.content6, R.string.content7, R.string.content8, R.string.content9
			};
	public static int RESULT_FOR_NEW_TWITTER  = 0;
	private int start = 0;
	private static int refreshCnt = 0;
	private myListView list;
	private ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();
	private SimpleAdapter listItemAdapter;
	private Handler mHandler;
	private Context context;

	private LinearLayout ll;
	private Intent mIntent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_for_twitter);
		context = this;
		list = (myListView) findViewById(R.id.list);
		list.setMyListViewListener(this);
		list.setPullLoadEnable(true);

		ll = (LinearLayout) findViewById(R.id.linearlayout_newitem);
		ll.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ComponentName componentName = new ComponentName(XListViewActivity.this, NewTwitterActivity.class);
				mIntent = new Intent();
				mIntent.setComponent(componentName);
				//startActivity(mIntent);
				startActivityForResult(mIntent,RESULT_FOR_NEW_TWITTER);
			}
		});


		for(int i = 0; i < 5; i++){
			HashMap<String, Object> map = new HashMap<String, Object>();

			map.put("image_head",imageIds[i % 9]);
			map.put("uname", getString(userNames[i % 9]));
			map.put("time", getString(userTimes[i % 9]));
			map.put("content", getString(comment[i % 9]));
			map.put("image_book", imageId2s[i % 9]);
			listItem.add(map);
		}

		listItemAdapter = new SimpleAdapter(this,listItem, R.layout.list_raw,
				new String[]{"image_head","uname","time","content","image_book"},
				new int[]{R.id.list_image_head,R.id.list_uname,R.id.list_time, R.id.list_content, R.id.list_image_book});

		list.setAdapter(listItemAdapter);
		mHandler = new Handler();
	}


	private void onLoad() {
		list.stopRefresh();
		list.stopLoadMore();
		list.setRefreshTime("刚刚");
	}


	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		mHandler.postDelayed(new Runnable() {
			@Override
			public void run() {
				start = ++refreshCnt;
				listItem.clear();
				for (int i = 0; i < 5; i++) {
					HashMap<String, Object> map = new HashMap<String, Object>();

					map.put("image_head", imageIds[i % 9]);
					map.put("uname", getString(userNames[i % 9]));
					map.put("time", getString(userTimes[i % 9]));
					map.put("content", getString(comment[i % 9]));
					map.put("image_book", imageId2s[i % 9]);
					listItem.add(map);
				}

				listItemAdapter = new SimpleAdapter(context, listItem, R.layout.list_raw,
						new String[]{"image_head", "uname", "time", "content", "image_book"},
						new int[]{R.id.list_image_head, R.id.list_uname, R.id.list_time, R.id.list_content, R.id.list_image_book});

				list.setAdapter(listItemAdapter);
				onLoad();
			}
		}, 2000);
	}

	@Override
	public void onLoadMore() {
		mHandler.postDelayed(new Runnable() {
			@Override
			public void run() {
				for(int i = 5; i < 9; i++){
					HashMap<String, Object> map = new HashMap<String, Object>();

					map.put("image_head",imageIds[i % 9]);
					map.put("uname", getString(userNames[i % 9]));
					map.put("time", getString(userTimes[i % 9]));
					map.put("content",  getString(comment[i % 9]));
					map.put("image_book", imageId2s[i % 9]);
					listItem.add(map);
				}

				listItemAdapter.notifyDataSetChanged();
				onLoad();
			}
		}, 2000);
	}

	public void afterSend(){
		mHandler.postDelayed(new Runnable(){
			@Override
			public void run() {
				start = ++refreshCnt;
				listItem.clear();
				for(int i = 0; i < 9 ; i++){
					HashMap<String, Object> map = new HashMap<String, Object>();

					map.put("image_head",imageIds[i % 9]);
					map.put("uname", getString(userNames[i % 9]));
					map.put("time", getString(userTimes[i % 9]));
					map.put("content",  getString(comment[i % 9]));
					map.put("image_book", imageId2s[i % 9]);
					listItem.add(map);
				}

				listItemAdapter = new SimpleAdapter(context,listItem, R.layout.list_raw,
						new String[]{"image_head","uname","time","content","image_book"},
						new int[]{R.id.list_image_head,R.id.list_uname,R.id.list_time, R.id.list_content, R.id.list_image_book});

				list.setAdapter(listItemAdapter);
				onLoad();
			}
		}, 2000);
	}

	public void write() {
		// TODO Auto-generated method stub
		mHandler.postDelayed(new Runnable() {
			@Override
			public void run() {
				start = ++refreshCnt;
				listItem.clear();
				HashMap<String, Object> map1 = new HashMap<String, Object>();

				map1.put("image_head", imageIds[6]);
				map1.put("uname", getString(userNames[6]));
				map1.put("time", "刚刚");
				map1.put("content", getString(comment[6]));
				map1.put("image_book", imageId2s[6]);
				listItem.add(map1);

				for (int i = 0; i < 5; i++) {
					HashMap<String, Object> map = new HashMap<String, Object>();

					map.put("image_head", imageIds[i % 9]);
					map.put("uname", getString(userNames[i % 9]));
					map.put("time", getString(userTimes[i % 9]));
					map.put("content", getString(comment[i % 9]));
					map.put("image_book", imageId2s[i % 9]);
					listItem.add(map);
				}

				listItemAdapter = new SimpleAdapter(context, listItem, R.layout.list_raw,
						new String[]{"image_head", "uname", "time", "content", "image_book"},
						new int[]{R.id.list_image_head, R.id.list_uname, R.id.list_time, R.id.list_content, R.id.list_image_book});

				list.setAdapter(listItemAdapter);
				onLoad();
			}
		}, 2000);
	}

	public void onActivityResult(int requestCode, int resultCode, Intent intent){

		if (requestCode == RESULT_FOR_NEW_TWITTER)
		{
			write();
		}
	}
}