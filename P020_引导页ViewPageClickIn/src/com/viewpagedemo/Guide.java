package com.viewpagedemo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.widget.ImageView;

public class Guide extends Activity implements OnPageChangeListener {
	private MyViewPager vp;
	private ViewPagerAdapter vpAdapter;
	private ImageView[] dots;
	private int[] ids = { R.id.iv1, R.id.iv2, R.id.iv3 };// ����
	private int[] idss = { R.drawable.guide_one, R.drawable.guide_two,
			R.drawable.guide_three };
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
		vp = (MyViewPager) findViewById(R.id.viewpager);
		vpAdapter = new ViewPagerAdapter(idss, this, Guide.this, vp);
		vp.setAdapter(vpAdapter);
		vp.setOnPageChangeListener(this);
	}

	private void initDots() {
		dots = new ImageView[idss.length];
		for (int i = 0; i < idss.length; i++) {
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
