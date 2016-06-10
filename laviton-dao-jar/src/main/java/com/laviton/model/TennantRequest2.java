package com.laviton.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class TennantRequest2 implements Serializable,Cloneable {
	String accesstoken;

	@JsonGetter
	public String getAccesstoken() {
		return accesstoken;
	}
@JsonSetter
	public void setAccesstoken(String accesstoken) {
		this.accesstoken = accesstoken;
	}
	

}
