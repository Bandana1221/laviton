package com.laviton.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class LoginRequesst implements Serializable,Cloneable{
	
	
	String username;
	String password;
	String type;
	
	
	@JsonGetter
	public String getUsername() {
		return username;
	}
	@JsonSetter
	public void setUsername(String username) {
		this.username = username;
	}
	@JsonGetter
	public String getPassword() {
		return password;
	}
	@JsonSetter
	public void setPassword(String password) {
		this.password = password;
	}
	@JsonGetter
	public String getType() {
		return type;
	}
	@JsonSetter
	public void setType(String type) {
		this.type = type;
	}
	

}
