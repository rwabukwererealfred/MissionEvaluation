package com.mission.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mission.dao.EmployeeDao;
import com.mission.domain.Employee;
import com.mission.domain.Role;

@Service
public class AuthenticationServiceImpl implements IAuthenticationService {
	
	@Autowired
	private EmployeeDao employeeDao;
	
	@Autowired
	private PasswordEncoder encoder;

	
	@Override
	public String createAccount(String username, String password, String nationalId) {
		Optional<Employee> em = employeeDao.findById(nationalId);
		if(em.isPresent()) {
			Optional<Employee>findUsername = employeeDao.findByUsername(username);
			if(!findUsername.isPresent()) {
			if(em.get().getUsername()== null && em.get().getPassword()== null) {
		em.get().setAccess(true);
		em.get().setUsername(username);
		em.get().setPassword(encoder.encode(password));
		employeeDao.save(em.get());
		return "success";
			}else {
				return "username and password are arleady exist to this account";
			}
			}else {
				return "username is arleady exist";
			}
		}else {
			return "National Id is not exist";
		}
		
	}


	@Override
	public void createRole(Role role) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void userCreation(Employee employee) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public Optional<Employee> findByUsername(String username) {
		
		return employeeDao.findByUsername(username);
	}

}
