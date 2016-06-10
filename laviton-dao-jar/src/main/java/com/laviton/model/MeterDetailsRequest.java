package com.laviton.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class MeterDetailsRequest implements Serializable,Cloneable{

	String accesstoken;
	String tennantid;
	String meterid;
	@JsonGetter
	public String getAccesstoken() {
		return accesstoken;
	}
	@JsonSetter
	public void setAccesstoken(String accesstoken) {
		this.accesstoken = accesstoken;
	}
	
	@JsonGetter
	public String getTennantid() {
		return tennantid;
	}
	@JsonSetter
	public void setTennantid(String tennantid) {
		this.tennantid = tennantid;
	}
	@JsonGetter
	public String getMeterid() {
		return meterid;
	}
	@JsonSetter
	public void setMeterid(String meterid) {
		this.meterid = meterid;
	}
	
}
