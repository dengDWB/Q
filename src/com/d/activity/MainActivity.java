package com.d.activity;


import com.d.function.Push;
import com.d.table.WeatherInfo;
import com.example.q.R;

import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tianqi);
		tv = (TextView) findViewById(R.id.tv);
		getTianInfor();
	}

	public void getTianInfor() {

		Push push = new Push();
		push.saveId();
		push.setPush();
	}
	
}
