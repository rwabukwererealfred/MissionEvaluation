package com.mission.request;


import javax.validation.constraints.Size;

public class SignUpForm {

	private String userId;
	
	@Size(min = 4, message = "minimun username must be atleast 4 digit")
	
	private String username;
	
	@Size(min = 6, message = "minimun password must be atleast 6 digit")
	private String password;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
