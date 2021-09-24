package com.mission.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Role {

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int id;
	
	@Column(name="NAME")
	@Enumerated(EnumType.STRING)
	private ERole name;
	
	
	
	
	public static enum ERole{
		ADVISOR, EMPLOYEE, HR
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public ERole getName() {
		return name;
	}


	public void setName(ERole name) {
		this.name = name;
	}


	public Role(ERole name) {
		super();
		
		this.name = name;
	}


	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
