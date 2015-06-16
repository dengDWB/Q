package com.d.fragment;


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

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class HomePageFragment extends Fragment implements OnClickListener {
	
	private LinearLayout weatherLayout;
	private TextView tvDynamic, tvWeather, tvNew, tvTips, tvChange, tvChange1;
	private LocationClient locationClient = null;
	private Context context ;
	private String city = "";
	private Thread thread;
	private final int MSG_SUCCESS = 1;
	private final int MSG_FAILURE = 0;
	private WeatherInfo wi;
	private LocationInfo locationInfo;
	private List<String> list;
	private StringBuffer sb = new StringBuffer();
	View homepageLayout;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		context =getActivity();
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
				thread = new Thread(runnable);
				thread.start();

			}
		});
		locationClient.start();
		locationClient.requestLocation();
	}
	
	public void bindAndListener() {
		weatherLayout = (LinearLayout) homepageLayout.findViewById(R.id.weatherLayout);
		tvDynamic = (TextView) homepageLayout.findViewById(R.id.tvDynamic);
		tvWeather = (TextView) homepageLayout.findViewById(R.id.tvWeather);
		tvNew = (TextView) homepageLayout.findViewById(R.id.tvNew);
		tvTips = (TextView) homepageLayout.findViewById(R.id.tvTips);
		tvChange = (TextView) homepageLayout.findViewById(R.id.tvChange);
		tvChange1 = (TextView) homepageLayout.findViewById(R.id.tvChange1);

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

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		homepageLayout = inflater.inflate(R.layout.activity_home, container, false);
		bindAndListener();
		return homepageLayout;
	}
	
	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 1) {
				list = wi.getList();
				for (int i = 0; i < list.size(); i++) {
					wi.getDateWeather(list.get(i));
				}
				System.out.println(locationInfo.getLocationInfo());
				tvChange1.setText(locationInfo.getLocationInfo());
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
	
	public Handler getHandler() {
		return handler;
	}

}
