package com.laviton.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class AssignMeter implements Serializable {
	String success;
	String meterid;
	String tennantid;
	String error;
	@JsonGetter
	public String getSuccess() {
		return success;
	}
	@JsonSetter
	public void setSuccess(String success) {
		this.success = success;
	}
	@JsonGetter
	public String getMeterid() {
		return meterid;
	}
	@JsonSetter
	public void setMeterid(String meterid) {
		this.meterid = meterid;
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
	public String getError() {
		return error;
	}
	@JsonSetter
	public void setError(String error) {
		this.error = error;
	}
	
	

}
