package com.laviton.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class TennantResponse implements Serializable{
String success;
String error;
String tennantid;
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
public String getTennantid() {
	return tennantid;
}
@JsonSetter
public void setTennantid(String tennantid) {
	this.tennantid = tennantid;
}

}
