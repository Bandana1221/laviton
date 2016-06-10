package com.laviton.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table
public class MeterDetails {
	@Id
	 @GeneratedValue(strategy= GenerationType.AUTO)
	@Column
	private int meter_id;
	@Column
	private String meter_name;
	@Column
	private String meter_class;
	@Column
	private String meter_address;
	@Column
	private String meter_type;
	@Column
	private String meter_status;
	@Column
	private String meterassign;
	@Column
	private String meter_numpoints;
	@Column
	private String meter_age;
	@Column
	private float meter_positive_energy;
	@Column
	private float meter_negative_energy;
	@Column
	private float meter_peak_demand;
	@Column
	@OneToMany(mappedBy="meterDetails",cascade=CascadeType.ALL)
	private List<MeterLogs> meterlogs;
	@ManyToMany(mappedBy="meetings")
	private Set<TennantDetails> employees = new HashSet<TennantDetails>();
	
	public String getMeterassign() {
		return meterassign;
	}
	public void setMeterassign(String meterassign) {
		this.meterassign = meterassign;
	}
	public List<MeterLogs> getMeterlogs() {
		return meterlogs;
	}
	public void setMeterlogs(List<MeterLogs> meterlogs) {
		this.meterlogs = meterlogs;
	}
	
	public Set<TennantDetails> getEmployees() {
		return employees;
	}
	public void setEmployees(Set<TennantDetails> employees) {
		this.employees = employees;
	}
	public int getMeter_id() {
		return meter_id;
	}
	public void setMeter_id(int meter_id) {
		this.meter_id = meter_id;
	}
	public String getMeter_name() {
		return meter_name;
	}
	public void setMeter_name(String meter_name) {
		this.meter_name = meter_name;
	}
	public String getMeter_class() {
		return meter_class;
	}
	public void setMeter_class(String meter_class) {
		this.meter_class = meter_class;
	}
	public String getMeter_address() {
		return meter_address;
	}
	public void setMeter_address(String meter_address) {
		this.meter_address = meter_address;
	}
	public String getMeter_type() {
		return meter_type;
	}
	public void setMeter_type(String meter_type) {
		this.meter_type = meter_type;
	}
	public String getMeter_status() {
		return meter_status;
	}
	public void setMeter_status(String meter_status) {
		this.meter_status = meter_status;
	}
	public String getMeter_numpoints() {
		return meter_numpoints;
	}
	public void setMeter_numpoints(String meter_numpoints) {
		this.meter_numpoints = meter_numpoints;
	}
	public String getMeter_age() {
		return meter_age;
	}
	public void setMeter_age(String meter_age) {
		this.meter_age = meter_age;
	}
	public float getMeter_positive_energy() {
		return meter_positive_energy;
	}
	public void setMeter_positive_energy(float meter_positive_energy) {
		this.meter_positive_energy = meter_positive_energy;
	}
	public float getMeter_negative_energy() {
		return meter_negative_energy;
	}
	public void setMeter_negative_energy(float meter_negative_energy) {
		this.meter_negative_energy = meter_negative_energy;
	}
	public float getMeter_peak_demand() {
		return meter_peak_demand;
	}
	public void setMeter_peak_demand(float meter_peak_demand) {
		this.meter_peak_demand = meter_peak_demand;
	}
	
	

}
