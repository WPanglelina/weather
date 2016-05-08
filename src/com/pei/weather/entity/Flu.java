package com.pei.weather.entity;

import java.io.Serializable;

public class Flu implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String brf;

	private String txt;

	public void setBrf(String brf) {
		this.brf = brf;
	}

	public String getBrf() {
		return this.brf;
	}

	public void setTxt(String txt) {
		this.txt = txt;
	}

	public String getTxt() {
		return this.txt;
	}

}