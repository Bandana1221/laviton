package com.laviton.model;

import java.io.Serializable;

import org.hibernate.cfg.Settings;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class InputJson implements Serializable,Cloneable{
	
String time;
@JsonGetter
public String getTime() {
	return time;
}
@JsonSetter
public void setTime(String time) {
	this.time = time;
}

}
