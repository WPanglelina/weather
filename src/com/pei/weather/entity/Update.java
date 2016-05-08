package com.pei.weather.entity;

import java.io.Serializable;

public class Update implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String loc;

	private String utc;

	public void setLoc(String loc) {
		this.loc = loc;
	}

	public String getLoc() {
		return this.loc;
	}

	public void setUtc(String utc) {
		this.utc = utc;
	}

	public String getUtc() {
		return this.utc;
	}

}
