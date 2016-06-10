package com.laviton.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class TennantRequest1 implements Serializable,Cloneable {
	String acesstoken;
	String tennantid;
	@JsonGetter
	public String getAcesstoken() {
		return acesstoken;
	}
	@JsonSetter
	public void setAcesstoken(String acesstoken) {
		this.acesstoken = acesstoken;
	}
	@JsonGetter
	public String getTennantid() {
		return tennantid;
	}
	@JsonSetter
	public void setTennantid(String tennantid) {
		this.tennantid = tennantid;
	}
	

}
