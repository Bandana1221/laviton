package com.laviton.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table
public class DateData {
	@Id
	 @GeneratedValue(strategy= GenerationType.AUTO)
	@Column
	private int id;
	@Column
	private int tennantid;
	@Column
	private Date date;
	@Column
	private float energy;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTennantid() {
		return tennantid;
	}
	public void setTennantid(int tennantid) {
		this.tennantid = tennantid;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public float getEnergy() {
		return energy;
	}
	public void setEnergy(float energy) {
		this.energy = energy;
	}
	

}
