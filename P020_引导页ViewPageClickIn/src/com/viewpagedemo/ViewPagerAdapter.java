package com.viewpagedemo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class ViewPagerAdapter extends PagerAdapter {
	private Context mContext;
	private MyViewPager mViewPager;
	private int[] idss;
	private Activity activity;

	public ViewPagerAdapter(int[] idss, Context mContext, Activity activity,
			MyViewPager mViewPager) {
		this.mContext = mContext;
		this.mViewPager = mViewPager;
		this.idss = idss;
		this.activity = activity;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View) object);
		mViewPager.removeViewFromPostion(position);
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		View view = null;
		if (position == idss.length - 1) {
			view = LayoutInflater.from(mContext).inflate(R.layout.three, null);
			Button start_btn = (Button) view.findViewById(R.id.start_btn);
			start_btn.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					Intent i = new Intent(mContext, MainActivity.class);
					activity.startActivity(i);
					activity.finish();
				}
			});
		} else {
			ImageView imageview = new ImageView(mContext);
			imageview.setImageResource(idss[position]);
			imageview.setScaleType(ScaleType.CENTER_CROP);
			view = imageview;
		}
		container.addView(view);
		mViewPager.setViewForPosition(view, position);
		return view;
	}

	@Override
	public int getCount() {
		return idss.length;
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return (arg0 == arg1);
	}
}
