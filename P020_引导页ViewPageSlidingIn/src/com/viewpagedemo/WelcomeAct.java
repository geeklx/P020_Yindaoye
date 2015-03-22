package com.viewpagedemo;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;

public class WelcomeAct extends Activity {
	private boolean isFirstIn = true;
	private static final int TIME = 2000;
	private static final int GO_HOME = 1000;
	private static final int GO_GUIDE = 1001;

	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case GO_HOME:
				GO_HOME();
				break;
			case GO_GUIDE:
				GO_GUIDE();
				break;
			default:
				break;
			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);
		init();
	}

	private void init() {
		SharedPreferences perPreferences = getSharedPreferences("liangxiao",
				MODE_PRIVATE);
		isFirstIn = perPreferences.getBoolean("isFirstIn", true);
		if (!isFirstIn) {
			mHandler.sendEmptyMessageDelayed(GO_HOME, TIME);
		} else {
			mHandler.sendEmptyMessageDelayed(GO_GUIDE, TIME);
			Editor editor = perPreferences.edit();
			editor.putBoolean("isFirstIn", false);
			editor.commit();
		}
	}

	private void GO_HOME() {
		Intent i = new Intent(WelcomeAct.this, MainActivity.class);
		startActivity(i);
		finish();
	}

	private void GO_GUIDE() {
		Intent i = new Intent(WelcomeAct.this, Guide.class);
		startActivity(i);
		finish();
	}
}
