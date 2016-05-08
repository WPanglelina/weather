package com.pei.weather.entity;

import java.io.Serializable;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

public class HeWeather implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<HeWeatherData> heWeatherData;

	@JSONField(name = "HeWeather data service 3.0")
	public List<HeWeatherData> getHeWeatherData() {
		return this.heWeatherData;
	}

	@JSONField(name = "HeWeather data service 3.0")
	public void setHeWeatherData(List<HeWeatherData> heWeatherData) {
		this.heWeatherData = heWeatherData;
	}

}