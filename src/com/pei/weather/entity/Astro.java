package com.pei.weather.entity;

import java.io.Serializable;

public class Astro implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String mr;

	private String ms;

	private String sr;

	private String ss;

	public void setMr(String mr) {
		this.mr = mr;
	}

	public String getMr() {
		return this.mr;
	}

	public void setMs(String ms) {
		this.ms = ms;
	}

	public String getMs() {
		return this.ms;
	}

	public void setSr(String sr) {
		this.sr = sr;
	}

	public String getSr() {
		return this.sr;
	}

	public void setSs(String ss) {
		this.ss = ss;
	}

	public String getSs() {
		return this.ss;
	}

}
