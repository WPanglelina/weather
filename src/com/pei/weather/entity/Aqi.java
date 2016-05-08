package com.pei.weather.entity;

import java.io.Serializable;

public class Aqi implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private City city;

	public void setCity(City city) {
		this.city = city;
	}

	public City getCity() {
		return this.city;
	}

}
