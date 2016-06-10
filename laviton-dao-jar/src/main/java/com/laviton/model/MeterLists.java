package com.laviton.model;

import java.io.Serializable;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class MeterLists implements Serializable{
	String success;
	ArrayList<MeterList> m=new ArrayList<MeterList>();
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
	public ArrayList<MeterList> getM() {
		return m;
	}
	@JsonGetter
	public void setM(ArrayList<MeterList> m) {
		this.m = m;
	}
	@JsonGetter
	public String getError() {
		return error;
	}
	@JsonGetter
	public void setError(String error) {
		this.error = error;
	}
	

}
