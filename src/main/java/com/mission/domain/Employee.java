package com.mission.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;



@Entity
public class Employee {

	@Id
	private String natinalityID;
	private String firstName;
	private String lastName;
	@Email(message ="wrong email sir")
	private String email;
	@Past(message="Wrong Date of birth")
	private Date dateofbirth;
	
	
	private String phoneNumber;
	private boolean access;
	private String code;
	
	
	@Column(unique = true)
	private String username;
	
	private String password;
	
	@OneToOne
	@JoinColumn(name="role")
	private Role role;
	
	public String getNatinalityID() {
		return natinalityID;
	}
	public void setNatinalityID(String natinalityID) {
		this.natinalityID = natinalityID;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getDateofbirth() {
		return dateofbirth;
	}
	public void setDateofbirth(Date dateofbirth) {
		this.dateofbirth = dateofbirth;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public boolean isAccess() {
		return access;
	}
	public void setAccess(boolean access) {
		this.access = access;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
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
	public Employee(String natinalityID, String firstName, String lastName,
			@Email(message = "wrong email sir") String email, @Past(message = "Wrong Date of birth") Date dateofbirth,
			String phoneNumber, boolean access, String code,
			@Size(min = 4, message = "minimun username must be 4 digit") String username,
			@Size(min = 6, message = "minimun password must be 6 digit") String password) {
		super();
		this.natinalityID = natinalityID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.dateofbirth = dateofbirth;
		this.phoneNumber = phoneNumber;
		this.access = access;
		this.code = code;
		this.username = username;
		this.password = password;
	}
	public Employee() {
		
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	
}
