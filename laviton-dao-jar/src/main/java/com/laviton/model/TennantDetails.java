package com.laviton.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class TennantDetails {
	
	@Id
	 @GeneratedValue(strategy= GenerationType.AUTO)
		@Column
		private int tennant_id;
		@Column
		private String tennant_name;
		@Column
		private String tennant_display_name;
		@Column
		private String tennant_address;
		@Column
		private String tennant_start_date;
		@Column
		private String tennant_end_date;
		@Column
		private String tennant_group;
		@Column
		private String tennant_last_billdate;
		@Column
		private String tennant_next_billdate;
		@Column
		private String tennant_account_number;
		@Column
		private String tennant_billing_interval;
		@Column
		private int customer_rate;
		@ManyToMany(cascade = {CascadeType.ALL})
		@JoinTable(name="TENNANT_METER", 
					joinColumns={@JoinColumn(name="tennant_id")}, 
					inverseJoinColumns={@JoinColumn(name="meter_id")})
		private Set<MeterDetails> meetings = new HashSet<MeterDetails>();
		@ManyToOne
		@JoinColumn(name="tennantDetails1")
		private Property_Manager_Details tennantDetails1;
		
		
		public Set<MeterDetails> getMeetings() {
			return meetings;
		}
		public void setMeetings(Set<MeterDetails> meetings) {
			this.meetings = meetings;
		}
		public Property_Manager_Details getTennantDetails1() {
			return tennantDetails1;
		}
		public void setTennantDetails1(Property_Manager_Details tennantDetails1) {
			this.tennantDetails1 = tennantDetails1;
		}
		public Property_Manager_Details getManagerdetails() {
			return tennantDetails1;
		}
		public void setManagerdetails(Property_Manager_Details tennantDetails1) {
			this.tennantDetails1 = tennantDetails1;
		}
		public int getTennant_id() {
			return tennant_id;
		}
		public void setTennant_id(int tennant_id) {
			this.tennant_id = tennant_id;
		}
		public String getTennant_name() {
			return tennant_name;
		}
		public void setTennant_name(String tennant_name) {
			this.tennant_name = tennant_name;
		}
		public String getTennant_display_name() {
			return tennant_display_name;
		}
		public void setTennant_display_name(String tennant_display_name) {
			this.tennant_display_name = tennant_display_name;
		}
		public String getTennant_address() {
			return tennant_address;
		}
		public void setTennant_address(String tennant_address) {
			this.tennant_address = tennant_address;
		}
		public String getTennant_start_date() {
			return tennant_start_date;
		}
		public void setTennant_start_date(String tennant_start_date) {
			this.tennant_start_date = tennant_start_date;
		}
		public String getTennant_end_date() {
			return tennant_end_date;
		}
		public void setTennant_end_date(String tennant_end_date) {
			this.tennant_end_date = tennant_end_date;
		}
		public String getTennant_group() {
			return tennant_group;
		}
		public void setTennant_group(String tennant_group) {
			this.tennant_group = tennant_group;
		}
		public String getTennant_last_billdate() {
			return tennant_last_billdate;
		}
		public void setTennant_last_billdate(String tennant_last_billdate) {
			this.tennant_last_billdate = tennant_last_billdate;
		}
		public String getTennant_next_billdate() {
			return tennant_next_billdate;
		}
		public void setTennant_next_billdate(String tennant_next_billdate) {
			this.tennant_next_billdate = tennant_next_billdate;
		}
		public String getTennant_account_number() {
			return tennant_account_number;
		}
		public void setTennant_account_number(String tennant_account_number) {
			this.tennant_account_number = tennant_account_number;
		}
		public String getTennant_billing_interval() {
			return tennant_billing_interval;
		}
		public void setTennant_billing_interval(String tennant_billing_interval) {
			this.tennant_billing_interval = tennant_billing_interval;
		}
		public int getCustomer_rate() {
			return customer_rate;
		}
		public void setCustomer_rate(int customer_rate) {
			this.customer_rate = customer_rate;
		}
		
}
