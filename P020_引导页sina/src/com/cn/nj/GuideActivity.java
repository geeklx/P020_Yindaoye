package com.cn.nj;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GuideActivity extends Activity implements OnPageChangeListener {
	private ViewPager vp;
	private ViewPagerAdapter vpAdapter;
	private List<View> views;
	private ImageView[] dots;
	private TextView[] dots_text;
	private int currentIndex;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.guide);
		initViews();
		initDots();
	}

	private void initViews() {
		LayoutInflater inflater = LayoutInflater.from(this);
		views = new ArrayList<View>();
		views.add(inflater.inflate(R.layout.what_new_one, null));
		views.add(inflater.inflate(R.layout.what_new_two, null));
		vpAdapter = new ViewPagerAdapter(views, this);
		vp = (ViewPager) findViewById(R.id.viewpager);
		vp.setAdapter(vpAdapter);
		vp.setOnPageChangeListener(this);
	}

	// 点点移动
	private void initDots() {
		// 点点部分
		LinearLayout ll = (LinearLayout) findViewById(R.id.ll);
		// 文字部分
		LinearLayout ll_text = (LinearLayout) findViewById(R.id.ll_text);
		dots = new ImageView[views.size()];
		dots_text = new TextView[views.size()];
		for (int i = 0; i < views.size(); i++) {
			dots[i] = (ImageView) ll.getChildAt(i);
			dots[i].setEnabled(true);
			dots_text[i] = (TextView) ll_text.getChildAt(i);
			dots_text[i].setTextColor(Color.argb(100, 46, 44, 45));// true 蓝
		}
		currentIndex = 0;
		dots[currentIndex].setEnabled(false);
		dots_text[currentIndex].setTextColor(Color.argb(100, 66, 174, 203));// false黑
	}

	// 点点文字联动部分，changed
	private void setCurrentDot(int position) {
		if (position < 0 || position > views.size() - 1
				|| currentIndex == position) {
			return;
		}
		// 点点部分
		dots[position].setEnabled(false);
		dots[currentIndex].setEnabled(true);
		// 文字部分
		dots_text[position].setTextColor(Color.argb(100, 66, 174, 203));// false黑
		dots_text[currentIndex].setTextColor(Color.argb(100, 46, 44, 45));// true蓝
		currentIndex = position;
	}

	// 文字部分
	private void setCurrentDot_text(int position) {
		if (position < 0 || position > views.size() - 1
				|| currentIndex == position) {
			return;
		}
		// dots_text[position].setEnabled(false);
		dots_text[currentIndex].setTextColor(Color.argb(100, 66, 174, 203));// false黑
		// dots_text[currentIndex].setEnabled(true);
		dots_text[currentIndex].setTextColor(Color.argb(100, 46, 44, 45));// true蓝
		currentIndex = position;
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
	}

	@Override
	public void onPageSelected(int arg0) {
		// setCurrentDot_text(arg0);
		setCurrentDot(arg0);

	}

}
