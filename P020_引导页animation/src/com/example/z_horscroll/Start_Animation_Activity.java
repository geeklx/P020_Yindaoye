package com.example.z_horscroll;

import com.example.android.scrolltricks.ObservableHScrollView;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

public class Start_Animation_Activity extends Activity implements
		ObservableHScrollView.Callbacks {

	ObservableHScrollView mObservableHScrollView;
	ImageView pao, sun;
	int pao1 = 0, pao2 = 0;
	int status = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.start_animation);
		pao1 = R.drawable.boy01;
		pao2 = R.drawable.boy02;
		pao = (ImageView) findViewById(R.id.pao);
		sun = (ImageView) findViewById(R.id.sun);
		mObservableHScrollView = (ObservableHScrollView) findViewById(R.id.mObservableHScrollView);
		mObservableHScrollView.setCallbacks(this);
		mObservableHScrollView.getViewTreeObserver().addOnGlobalLayoutListener(
				new ViewTreeObserver.OnGlobalLayoutListener() {
					@Override
					public void onGlobalLayout() {
						onScrollChanged(mObservableHScrollView.getScrollX());
					}
				});
		rotateAnimation(sun);
	}

	/*
	 * Ì«Ñô¶¯»­²¿·Ö
	 */
	public void rotateAnimation(View view) {
		RotateAnimation animation = new RotateAnimation(0f, 360f,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		LinearInterpolator lin = new LinearInterpolator();
		animation.setInterpolator(lin);
		animation.setDuration(3000);
		animation.setRepeatCount(-1);
		animation.setFillAfter(true);
		view.startAnimation(animation);
	}

	private int scrollx = 0;
	int alpha = 100;
	int hwidth = 0;
	int perwidth = 80;

	@Override
	public void onScrollChanged(int scrollX) {
		// TODO Auto-generated method stub
		if (hwidth == 0)
			hwidth = mObservableHScrollView.getWidth();

		if (scrollX - scrollx > perwidth) {
			scrollx = scrollX;
			if (status == 1) {
				status = 2;
				pao.setImageResource(pao2);
			} else {
				status = 1;
				pao.setImageResource(pao1);
			}
			// if (scrollX > hwidth - 500) {
			// if (alpha >= 0) {
			// pao.setAlpha(alpha);
			// alpha = alpha - 20;
			// }
			// }
		}

		if (scrollX - scrollx < -perwidth) {
			scrollx = scrollX;
			if (status == 1) {
				status = 2;
				pao.setImageResource(pao2);
			} else {
				status = 1;
				pao.setImageResource(pao1);
			}

			// if (scrollX > hwidth - 500) {
			// if (alpha <= 100) {
			// pao.setAlpha(alpha);
			// alpha = alpha + 20;
			// }
			// }
		}
		System.out.println("scrollX:" + scrollX);
		if (scrollX >= 1120) {
			Intent intent = new Intent(Start_Animation_Activity.this, BActivity.class);
			Start_Animation_Activity.this.startActivity(intent);
			Start_Animation_Activity.this.finish();
		}
	}

	@Override
	public void onDownMotionEvent() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUpOrCancelMotionEvent() {
		// TODO Auto-generated method stub

	}

}
