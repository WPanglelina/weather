package com.pei.weather.entity;

import java.io.Serializable;

public class Tmp implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String max;

	private String min;

	public void setMax(String max) {
		this.max = max;
	}

	public String getMax() {
		return this.max;
	}

	public void setMin(String min) {
		this.min = min;
	}

	public String getMin() {
		return this.min;
	}

}