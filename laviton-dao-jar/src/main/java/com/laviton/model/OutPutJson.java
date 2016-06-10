package com.laviton.model;

import java.io.Serializable;
import java.sql.Date;

public class OutPutJson implements Serializable {
	 String date;
	 String penergy;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getPenergy() {
		return penergy;
	}

	public void setPenergy(String penergy) {
		this.penergy = penergy;
	}
	
	

}
