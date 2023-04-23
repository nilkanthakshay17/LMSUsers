package com.cognizant.app.lms.users.model;

import com.cognizant.app.lms.users.annotation.UserEmail;
import com.cognizant.app.lms.users.annotation.UserFieldNotNull;
import com.cognizant.app.lms.users.annotation.UserPassword;

public class UserRequestModel {

	@UserFieldNotNull
	private String userName;
	
	@UserFieldNotNull
	@UserEmail
	private String userEmail;
	
	@UserFieldNotNull
	@UserPassword
	private String password;
	
	private boolean userAdmin;
	
	private String roles;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isUserAdmin() {
		return userAdmin;
	}
	public void setUserAdmin(boolean userAdmin) {
		this.userAdmin = userAdmin;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	
	
}
