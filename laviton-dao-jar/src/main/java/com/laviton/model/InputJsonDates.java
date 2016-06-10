package com.laviton.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class InputJsonDates implements Serializable,Cloneable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String accestoken;
	String edate;
	String sdate;
	String reqtype;
	String tennantid;
	@JsonGetter
	public String getSdate() {
		return sdate;
	}
	@JsonSetter
	public void setSdate(String sdate) {
		this.sdate = sdate;
	}
	@JsonGetter
	public String getAccestoken() {
		return accestoken;
	}
	@JsonSetter
	public void setAccestoken(String accestoken) {
		this.accestoken = accestoken;
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
	public String getEdate() {
		return edate;
	}
	@JsonSetter
	public void setEdate(String edate) {
		this.edate = edate;
	}
	@JsonGetter
	public String getReqtype() {
		return reqtype;
	}
	@JsonSetter
	public void setReqtype(String reqtype) {
		this.reqtype = reqtype;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	

}
