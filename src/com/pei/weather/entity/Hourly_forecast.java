package com.pei.weather.entity;

import java.io.Serializable;

public class Hourly_forecast implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String date;

	private String hum;

	private String pop;

	private String pres;

	private String tmp;

	private Wind wind;

	public void setDate(String date) {
		this.date = date;
	}

	public String getDate() {
		return this.date;
	}

	public void setHum(String hum) {
		this.hum = hum;
	}

	public String getHum() {
		return this.hum;
	}

	public void setPop(String pop) {
		this.pop = pop;
	}

	public String getPop() {
		return this.pop;
	}

	public void setPres(String pres) {
		this.pres = pres;
	}

	public String getPres() {
		return this.pres;
	}

	public void setTmp(String tmp) {
		this.tmp = tmp;
	}

	public String getTmp() {
		return this.tmp;
	}

	public void setWind(Wind wind) {
		this.wind = wind;
	}

	public Wind getWind() {
		return this.wind;
	}

}
