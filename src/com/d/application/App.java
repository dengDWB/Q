package com.d.application;

import com.avos.avoscloud.AVOSCloud;
import com.thinkland.sdk.android.JuheSDKInitializer;

import android.app.Application;

public class App extends Application {

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		JuheSDKInitializer.initialize(getApplicationContext());
		AVOSCloud.initialize(this,
				"dt75ctiqj3x8uim8rnfdy604xlli32kkgkeeztuvvta5lhwm",
				"1khszr6i86vvkartypf6twpogw8a7tvb73jal75is2bl0ueq");
	}

}
