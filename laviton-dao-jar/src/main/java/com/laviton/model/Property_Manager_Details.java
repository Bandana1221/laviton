package com.laviton.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Property_Manager_Details {
	@Id
	 @GeneratedValue(strategy= GenerationType.AUTO)
		@Column
		private int login_id;
		@Column
		private String login_user_name;
		@Column
		private String login_password;
		@Column
		private String login_email;
		@Column
		private String login_user_type;
		@Column
		private Boolean login_user_isVerify;
		@Column
		@OneToMany(mappedBy="tennantDetails1",cascade=CascadeType.ALL)
		private List<TennantDetails> tennantdetails;
		
		public List<TennantDetails> getTennantdetails() {
			return tennantdetails;
		}
		public void setTennantdetails(List<TennantDetails> tennantdetails) {
			this.tennantdetails = tennantdetails;
		}
		public int getLogin_id() {
			return login_id;
		}
		public void setLogin_id(int login_id) {
			this.login_id = login_id;
		}
		public String getLogin_user_name() {
			return login_user_name;
		}
		public void setLogin_user_name(String login_user_name) {
			this.login_user_name = login_user_name;
		}
		public String getLogin_password() {
			return login_password;
		}
		public void setLogin_password(String login_password) {
			this.login_password = login_password;
		}
		public String getLogin_email() {
			return login_email;
		}
		public void setLogin_email(String login_email) {
			this.login_email = login_email;
		}
		public String getLogin_user_type() {
			return login_user_type;
		}
		public void setLogin_user_type(String login_user_type) {
			this.login_user_type = login_user_type;
		}
		public Boolean getLogin_user_isVerify() {
			return login_user_isVerify;
		}
		public void setLogin_user_isVerify(Boolean login_user_isVerify) {
			this.login_user_isVerify = login_user_isVerify;
		}
		
		
}
