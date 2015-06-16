package com.d.table;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class WeatherInfo {

	private String todayTemp = "", todayWeather = "";
	private Map<String, String> dateWeather = new TreeMap<String, String>();
	private List<String> list = new ArrayList<String>();

	public WeatherInfo() {

	}

	public void setTodayTemp(String todayTemp) {
		this.todayTemp = todayTemp;
	}

	public String getTodayTemp() {
		return todayTemp;
	}

	public void setTodayWeather(String todayWeather) {
		this.todayWeather = todayWeather;
	}

	public String getTodayWeather() {
		return todayWeather;
	}

	public void setFutureWeather(String date, String dateInfo) {
		dateWeather.put(date, dateInfo);
	}

	public String getDateWeather(String date) {
		String weatherInfo = dateWeather.get(date);
		return weatherInfo;
	}
	
	public void setDate(String date){
		list.add(date);
	}
	
	public List<String> getList(){
		return list;
	}

}
