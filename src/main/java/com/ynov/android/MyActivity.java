package com.ynov.android;

import org.greenrobot.eventbus.EventBus;

import android.app.Activity;

public class MyActivity extends Activity {

	@Override
	public void onStart() {
		super.onStart();
		EventBus.getDefault().register(this); // register EventBus
	}

	@Override
	public void onStop() {
		EventBus.getDefault().unregister(this); // unregister EventBus
		super.onStop();
	}
}