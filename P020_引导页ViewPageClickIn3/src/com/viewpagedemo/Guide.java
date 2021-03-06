package com.viewpagedemo;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class Guide extends Activity implements OnPageChangeListener {
	private ViewPager vp;
	private ViewPagerAdapter vpAdapter;
	private List<View> views;
	private ImageView[] dots;
	private int[] ids = { R.id.iv1, R.id.iv2, R.id.iv3 };
	private Button start_btn;
	private SharedPreferences preferences;

	@SuppressLint("WorldReadableFiles")
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.guide);
		preferences = getSharedPreferences("count", MODE_WORLD_READABLE);
		int count = preferences.getInt("count", 0);
		// 判断程序与第几次运行，如果是第一次运行则跳转到引导页面
		if (count == 0) {
			Editor editor = preferences.edit();
			// 存入数据
			editor.putInt("count", ++count);
			// 提交修改
			editor.commit();
			initViews();
			initDots();
		} else {
			Intent intent = new Intent(this, WelcomeActivity.class);
			this.startActivity(intent);
			this.finish();
		}

	}

	private void initViews() {

		LayoutInflater inflater = LayoutInflater.from(this);
		views = new ArrayList<View>();
		views.add(inflater.inflate(R.layout.one, null));
		views.add(inflater.inflate(R.layout.two, null));
		views.add(inflater.inflate(R.layout.three, null));

		// adapter
		vpAdapter = new ViewPagerAdapter(views, this);
		vp = (ViewPager) findViewById(R.id.viewpager);
		vp.setPageTransformer(true, new ZoomOutPageTransformer());
		vp.setAdapter(vpAdapter);
		vp.setOnPageChangeListener(this);

		start_btn = (Button) views.get(2).findViewById(R.id.start_btn);
		start_btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(Guide.this, MainActivity.class);
				startActivity(i);
				finish();
			}
		});
	}

	private void initDots() {
		dots = new ImageView[views.size()];
		for (int i = 0; i < views.size(); i++) {
			dots[i] = (ImageView) findViewById(ids[i]);
		}
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {

	}

	@Override
	public void onPageSelected(int arg0) {
		for (int i = 0; i < ids.length; i++) {
			if (arg0 == i) {
				dots[i].setImageResource(R.drawable.check);
			} else {
				dots[i].setImageResource(R.drawable.encheck);
			}
		}

	}

}
