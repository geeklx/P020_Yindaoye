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
	private MyViewPager vp;
	private ViewPagerAdapter vpAdapter;
	private ImageView[] dots;
	private int[] ids = { R.id.iv1, R.id.iv2, R.id.iv3 };// ����
	private int[] idss = { R.drawable.guide_one, R.drawable.guide_two,
			R.drawable.guide_three };
	// private List<View> views;
	// private Button start_btn;
	private SharedPreferences preferences;

	@SuppressLint("WorldReadableFiles")
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.guide);
		preferences = getSharedPreferences("count", MODE_WORLD_READABLE);
		int count = preferences.getInt("count", 0);
		// �жϳ�����ڼ������У�����ǵ�һ����������ת������ҳ��
		if (count == 0) {
			Editor editor = preferences.edit();
			// ��������
			editor.putInt("count", ++count);
			// �ύ�޸�
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

		// LayoutInflater inflater = LayoutInflater.from(this);
		// views = new ArrayList<View>();
		// views.add(inflater.inflate(R.layout.one, null));
		// views.add(inflater.inflate(R.layout.two, null));
		// views.add(inflater.inflate(R.layout.three, null));

		// adapter
		vp = (MyViewPager) findViewById(R.id.viewpager);
		vpAdapter = new ViewPagerAdapter(idss, this, vp);
		vp.setAdapter(vpAdapter);
		vp.setOnPageChangeListener(this);
		// start_btn = (Button) views.get(2).findViewById(R.id.start_btn);
		// start_btn.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View arg0) {
		// Intent i = new Intent(Guide.this, MainActivity.class);
		// startActivity(i);
		// finish();
		// }
		// });
	}

	private void initDots() {
		dots = new ImageView[idss.length];
		for (int i = 0; i < idss.length; i++) {
			dots[i] = (ImageView) findViewById(ids[i]);
		}
	}

	private Boolean misScrolled = false;

	@Override
	public void onPageScrollStateChanged(int state) {
		switch (state) {
		case ViewPager.SCROLL_STATE_DRAGGING:
			misScrolled = false;
			break;
		case ViewPager.SCROLL_STATE_SETTLING:
			misScrolled = true;
			break;
		case ViewPager.SCROLL_STATE_IDLE:
			if (vp.getCurrentItem() == vp.getAdapter().getCount() - 1
					&& !misScrolled) {
				startActivity(new Intent(this, MainActivity.class));
				Guide.this.finish();
			}
			misScrolled = true;
			break;
		}
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
