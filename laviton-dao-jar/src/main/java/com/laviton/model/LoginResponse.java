package com.laviton.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class LoginResponse implements Serializable{
	String success;
	String error;
	String username;
	String accessrequest;
	
	@JsonGetter
	public String getError() {
		return error;
	}
	@JsonSetter
	public void setError(String error) {
		this.error = error;
	}
	@JsonGetter
	public String getUsername() {
		return username;
	}
	@JsonSetter
	public void setUsername(String username) {
		this.username = username;
	}
	@JsonGetter
	public String getAccessRequest() {
		return accessrequest;
	}
	@JsonSetter
	public void setAccessRequest(String accessRequest) {
		accessrequest = accessRequest;
	}
	@JsonGetter
	public String getSuccess() {
		return success;
	}
	@JsonSetter
	public void setSuccess(String success) {
		this.success = success;
	}
}
