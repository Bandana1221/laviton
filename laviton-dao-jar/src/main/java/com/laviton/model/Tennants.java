package com.laviton.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class Tennants implements Serializable
{
	String tennantid;
	String accesstoken;
	String tennantname;
	String displayname;
	String accountno;
	String enddate;
	String startdate;
	String address;
	String billinginterval;
	String nextbilldate;
	String lastbilldate;
	
	@JsonGetter
	public String getTennantid() {
		return tennantid;
	}
	@JsonSetter
	public void setTennantid(String tennantid) {
		this.tennantid = tennantid;
	}
	@JsonGetter
	public String getAccesstoken() {
		return accesstoken;
	}
	@JsonSetter
	public void setAccesstoken(String accesstoken) {
		this.accesstoken = accesstoken;
	}
	@JsonGetter
	public String getTennantname() {
		return tennantname;
	}
	@JsonSetter
	public void setTennantname(String tennantname) {
		this.tennantname = tennantname;
	}
	@JsonGetter
	public String getDisplayname() {
		return displayname;
	}
	@JsonSetter
	public void setDisplayname(String displayname) {
		this.displayname = displayname;
	}
	@JsonGetter
	public String getAccountno() {
		return accountno;
	}
	@JsonSetter
	public void setAccountno(String accountno) {
		this.accountno = accountno;
	}
	@JsonGetter
	public String getEnddate() {
		return enddate;
	}
	@JsonSetter
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	@JsonGetter
	public String getStartdate() {
		return startdate;
	}
	@JsonSetter
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	@JsonGetter
	public String getAddress() {
		return address;
	}
	@JsonSetter
	public void setAddress(String address) {
		this.address = address;
	}
	@JsonGetter
	public String getBillinginterval() {
		return billinginterval;
	}
	@JsonSetter
	public void setBillinginterval(String billinginterval) {
		this.billinginterval = billinginterval;
	}
	@JsonGetter
	public String getNextbilldate() {
		return nextbilldate;
	}
	@JsonSetter
	public void setNextbilldate(String nextbilldate) {
		this.nextbilldate = nextbilldate;
	}
	@JsonGetter
	public String getLastbilldate() {
		return lastbilldate;
	}
	@JsonSetter
	public void setLastbilldate(String lastbilldate) {
		this.lastbilldate = lastbilldate;
	}
	
}
