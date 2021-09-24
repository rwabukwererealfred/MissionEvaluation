package com.mission.response;

import java.util.List;

import com.mission.domain.Employee;


public class JwtResponse {

	private String id;
	private String username;
	private String password;
	private boolean active;
	private Employee employee;
	private List<String>role;
	private String token;
	
	


	public JwtResponse(String id, String username, String password, boolean active, Employee employee, List<String> role,
			String token) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.active = active;
		this.employee = employee;
		this.role = role;
		this.token = token;
	}


	public JwtResponse() {
		
	}


	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
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
	
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	


	public List<String> getRole() {
		return role;
	}


	public void setRole(List<String> role) {
		this.role = role;
	}


	public void setEmployee(Employee employee) {
		this.employee = employee;
	}


	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}


	public Employee getEmployee() {
		return employee;
	}
	
	
}
