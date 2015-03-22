package com.viewpagedemo;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class ViewPagerAdapter extends PagerAdapter {
	private Context mContext;
	private MyViewPager mViewPager;
	// private List<View> mView;
	private int[] idss;

	public ViewPagerAdapter(int[] idss, Context mContext, MyViewPager mViewPager) {
		this.mContext = mContext;
		this.mViewPager = mViewPager;
		// this.mView = mView;
		this.idss = idss;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View) object);
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		ImageView imageView = new ImageView(mContext);
		imageView.setImageResource(idss[position]);
		imageView.setScaleType(ScaleType.CENTER_CROP);
		container.addView(imageView);
		mViewPager.setObjectForPosition(imageView, position);
		// ((ViewPager) container).addView(mView.get(position));
		// mViewPager.setObjectForPosition(mView.get(position), position);
		return imageView;
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
