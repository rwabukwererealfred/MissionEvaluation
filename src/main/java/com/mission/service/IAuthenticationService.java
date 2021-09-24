package com.mission.service;

import java.util.Optional;

import com.mission.domain.Employee;
import com.mission.domain.Role;

public interface IAuthenticationService {

	public String createAccount(String username, String password, String nationalId);
	public void createRole(Role role);
	public void userCreation(Employee employee);
	public Optional<Employee>findByUsername(String username);
	
	
}
