package com.pei.weather.entity;

import java.io.Serializable;

public class Cond implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String code;

	private String txt;

	public void setCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return this.code;
	}

	public void setTxt(String txt) {
		this.txt = txt;
	}

	public String getTxt() {
		return this.txt;
	}

}
