package com.d.activity;

import java.util.List;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.d.function.Weather;
import com.d.table.LocationInfo;
import com.d.table.WeatherInfo;
import com.example.q.R;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends Activity implements OnClickListener {

	private LinearLayout weatherLayout;
	private TextView tvDynamic, tvWeather, tvNew, tvTips, tvChange, tvChange1;
	private LocationClient locationClient = null;
	private Context context = this;
	private String city = "";
	private Thread thread;
	private final int MSG_SUCCESS = 1;
	private final int MSG_FAILURE = 0;
	private WeatherInfo wi;
	private LocationInfo locationInfo;
	private List<String> list;
	private StringBuffer sb = new StringBuffer();

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 1) {
				list = wi.getList();
				for (int i = 0; i < list.size(); i++) {
					wi.getDateWeather(list.get(i));
				}
				Toast.makeText(context, "获取成功", Toast.LENGTH_SHORT).show();
			} else if (msg.what == 0) {
				Toast.makeText(context, "获取失败", Toast.LENGTH_SHORT).show();
			}
		};
	};

	Runnable runnable = new Runnable() {

		@Override
		public void run() {
			try {
				Looper.prepare();
				Weather weather = new Weather(city);
				wi = weather.getWi();
				Looper.loop();
			} catch (Exception e) {
				handler.obtainMessage(MSG_FAILURE).sendToTarget();
				return;
			}
			handler.obtainMessage(MSG_SUCCESS).sendToTarget();

		}

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		bindAndListener();
		locationClient = new LocationClient(context);
		LocationClientOption option = new LocationClientOption();
		option.setOpenGps(true);
		option.setLocationMode(LocationMode.Hight_Accuracy);
		option.setCoorType("bd09ll");
		option.setIsNeedAddress(true);
		option.setNeedDeviceDirect(true);
		locationClient.setLocOption(option);
		locationClient.registerLocationListener(new BDLocationListener() {
			public void onReceiveLocation(BDLocation location) {
				if (location == null) {
					return;
				}

				locationInfo = new LocationInfo();
				locationInfo.setLocation(location.getProvince()
						+ location.getCity() + location.getStreet());
				locationInfo.setTime(location.getTime());

				city = location.getCity();
				tvChange1.append(locationInfo.getLocationInfo());
				thread = new Thread(runnable);
				thread.start();

			}
		});
		locationClient.start();
	}

	public void bindAndListener() {
		weatherLayout = (LinearLayout) findViewById(R.id.weatherLayout);
		tvDynamic = (TextView) findViewById(R.id.tvDynamic);
		tvWeather = (TextView) findViewById(R.id.tvWeather);
		tvNew = (TextView) findViewById(R.id.tvNew);
		tvTips = (TextView) findViewById(R.id.tvTips);
		tvChange = (TextView) findViewById(R.id.tvChange);
		tvChange1 = (TextView) findViewById(R.id.tvChange1);

		tvDynamic.setOnClickListener(this);
		tvWeather.setOnClickListener(this);
		tvNew.setOnClickListener(this);
		tvTips.setOnClickListener(this);
		tvChange.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tvDynamic:

			break;
		case R.id.tvWeather:

			break;
		case R.id.tvNew:

			break;
		case R.id.tvTips:

			break;
		}

	}

	public Handler getHandler() {
		return handler;
	}

}
