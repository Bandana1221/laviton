package com.laviton.model;

import java.io.Serializable;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class TennantResponse2 implements Serializable {

	ArrayList<Tennants> responses=new ArrayList<Tennants>();
	String success;
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
	public String getError() {
		return error;
	}
@JsonSetter
	public void setError(String error) {
		this.error = error;
	}

	@JsonGetter
	public ArrayList<Tennants> getResponses() {
		return responses;
	}

	@JsonSetter
	public void setResponses(ArrayList<Tennants> responses) {
		this.responses = responses;
	}
	
}
