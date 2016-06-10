package com.laviton.model;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class MeterLogs {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column
	private int id;
	
	@ManyToOne
	@JoinColumn(name="meterDetails")
	private MeterDetails meterDetails;
	
	@Column
	private java.util.Date time;
	@Column
	private String date;
	@Column
	private String meter_log_low_alarm;
	@Column
	private String meter_log_high_alarm;
	@Column
	private float meter_log_total_positive_energy;
	@Column
	private float meter_log_total_negative_energy;
	@Column
	private String meter_log_peak_demand;
	
	
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public java.util.Date getTime() {
		return time;
	}
	public void setTime(java.util.Date time) {
		this.time = time;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public MeterDetails getMeterDetails() {
		return meterDetails;
	}
	public void setMeterDetails(MeterDetails meterDetails) {
		this.meterDetails = meterDetails;
	}
	
	public String getMeter_log_low_alarm() {
		return meter_log_low_alarm;
	}
	public void setMeter_log_low_alarm(String meter_log_low_alarm) {
		this.meter_log_low_alarm = meter_log_low_alarm;
	}
	public String getMeter_log_high_alarm() {
		return meter_log_high_alarm;
	}
	public void setMeter_log_high_alarm(String meter_log_high_alarm) {
		this.meter_log_high_alarm = meter_log_high_alarm;
	}
	public float getMeter_log_total_positive_energy() {
		return meter_log_total_positive_energy;
	}
	public void setMeter_log_total_positive_energy(float meter_log_total_positive_energy) {
		this.meter_log_total_positive_energy = meter_log_total_positive_energy;
	}
	public float getMeter_log_total_negative_energy() {
		return meter_log_total_negative_energy;
	}
	public void setMeter_log_total_negative_energy(float meter_log_total_negative_energy) {
		this.meter_log_total_negative_energy = meter_log_total_negative_energy;
	}
	public String getMeter_log_peak_demand() {
		return meter_log_peak_demand;
	}
	public void setMeter_log_peak_demand(String meter_log_peak_demand) {
		this.meter_log_peak_demand = meter_log_peak_demand;
	}
	

}
