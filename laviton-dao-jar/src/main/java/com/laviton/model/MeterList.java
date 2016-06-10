package com.laviton.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class MeterList implements Serializable{
	String metername;
	String meterid;
	String meterclass;
	String meteraddress;
	String metertype;
	String assigned;
	@JsonGetter
	public String getMetername() {
		return metername;
	}
	@JsonSetter
	public void setMetername(String metername) {
		this.metername = metername;
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
	public String getMeterclass() {
		return meterclass;
	}
	@JsonSetter
	public void setMeterclass(String meterclass) {
		this.meterclass = meterclass;
	}
	@JsonGetter
	public String getMeteraddress() {
		return meteraddress;
	}
	@JsonSetter
	public void setMeteraddress(String meteraddress) {
		this.meteraddress = meteraddress;
	}
	@JsonGetter
	public String getMetertype() {
		return metertype;
	}
	@JsonSetter
	public void setMetertype(String metertype) {
		this.metertype = metertype;
	}
	@JsonGetter
	public String getAssigned() {
		return assigned;
	}
	@JsonSetter
	public void setAssigned(String assigned) {
		this.assigned = assigned;
	}
	

}
