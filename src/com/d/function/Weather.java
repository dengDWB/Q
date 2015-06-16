package com.d.function;

import java.util.Iterator;

import org.json.JSONObject;

import android.content.Context;

import com.d.activity.HomeActivity;
import com.d.table.WeatherInfo;
import com.thinkland.sdk.android.DataCallBack;
import com.thinkland.sdk.android.JuheData;
import com.thinkland.sdk.android.Parameters;

public class Weather {

	private Context context;
	private String jsonStr = "";
	private WeatherInfo wi = new WeatherInfo();
	private String city = "",date="",weather="",temp="";

	public Weather() {
	}

	public Weather(String city) {
		this.city = city;
		getWeatherJson(city);
	}

	public void getWeatherJson(String city) {
		Parameters parameters = new Parameters();
		parameters.add("cityname", city);
		parameters.add("dtype", "JSON");
		parameters.add("format", 1);
		parameters.add("key", "20b391b78a045a601a9fb4b62c100fc1");
		JuheData.executeWithAPI(context, 39, "http://v.juhe.cn/weather/index",
				JuheData.GET, parameters, new DataCallBack() {

					@Override
					public void onFailure(int statusCode,
							String responseString, Throwable throwable) {

					}

					@Override
					public void onFinish() {
						resolveJson();

					}

					@Override
					public void onSuccess(int statusCode, String responseString) {
						jsonStr = responseString;
					}

				});

	}

	public void resolveJson() {

		try {
			JSONObject jb = new JSONObject(jsonStr).getJSONObject("result");
			JSONObject today = jb.getJSONObject("today");
			String toayTemperature = (String) today.get("temperature");
			wi.setTodayTemp(toayTemperature);
			String todayWeather = (String) today.get("weather");
			wi.setTodayWeather(todayWeather);
			JSONObject future = jb.getJSONObject("future");
			Iterator<String> it = future.keys();
			while (it.hasNext()) {
				JSONObject futureJs = future.getJSONObject(it.next());
				date = (String) futureJs.get("date");
				weather = (String) futureJs.get("weather");
				temp = (String) futureJs.get("temperature");
				wi.setDate(date);
				wi.setFutureWeather(date, temp + " " + weather);
			}
		} catch (Exception e) {
		}
		new HomeActivity().getHandler().getLooper().quit();

	}

	public WeatherInfo getWi() {
		return wi;
	}
	
	public String getDate(){
		return date;
	}
	
	public String getWeather(){
		return weather;
	}
	public String getTemp(){
		return temp;
	}

}
