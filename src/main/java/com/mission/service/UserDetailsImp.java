package com.mission.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.mission.domain.Employee;


public class UserDetailsImp implements UserDetails {
	
//	@Autowired
//	private UserRoleDao userRoleDao;
	
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String username;
	private String password;
	private Employee employee;
	
	private boolean active;
	
	private Collection<? extends GrantedAuthority>autho;
	
	
	
	
	public UserDetailsImp() {
		
	}
	
	public UserDetailsImp build(Employee user) {
//		List<GrantedAuthority>list = user.getUserRole().stream().filter(i->i.isActive()==true).
//				map(role -> new SimpleGrantedAuthority(role.getRole().getName().name())).collect(Collectors.toList());
		
		List<GrantedAuthority>li = Arrays.asList(new SimpleGrantedAuthority(user.getRole().getName().name()));
		
		return new UserDetailsImp(user.getNatinalityID(), user.getUsername(), user.getPassword(),
				user,isAccountNonExpired(),  li);	
	}

	

	

	public UserDetailsImp(String id, String username, String password, Employee employee, boolean active,
			Collection<? extends GrantedAuthority> autho) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.employee = employee;
		this.active = active;
		this.autho = autho;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		
		return true;
	}

	public String getId() {
		return id;
	}

	public boolean isActive() {
		return active;
	}

	public Employee getEmployee() {
		return employee;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return autho;
	}


}
