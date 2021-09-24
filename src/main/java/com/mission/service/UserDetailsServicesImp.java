package com.mission.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mission.dao.EmployeeDao;
import com.mission.domain.Employee;

@Service
public class UserDetailsServicesImp implements UserDetailsService {
	
	@Autowired
	private EmployeeDao employeeDao;

	@Transactional
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Employee user = employeeDao.findByUsername(username).orElseThrow(()->
		new UsernameNotFoundException("username not found with this username"+username));
		return new UserDetailsImp().build(user)	;
	}

}
