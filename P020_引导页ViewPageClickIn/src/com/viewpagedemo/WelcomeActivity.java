package com.viewpagedemo;

import java.util.Timer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class WelcomeActivity extends Activity {
	Timer timer;
	private ImageView imageView;
	Animation animation;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);
		imageView = (ImageView) findViewById(R.id.lunch_icon);
		animation = AnimationUtils.loadAnimation(this, R.anim.lunch_anim);
		imageView.setAnimation(animation);

		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(1000);
					startActivity(new Intent(WelcomeActivity.this,
							MainActivity.class));
					finish();

				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

}
