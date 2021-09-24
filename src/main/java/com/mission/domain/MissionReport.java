package com.mission.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class MissionReport implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String file;
	@Column(length=750)
	private String comment;
	private Date sendDate;
	private String missionObjectif;
	
	@Column(length = 750)
	private String advisorComment;
	
	@ManyToOne
	@JoinColumn(name="employeeMission")
	private EmployeeMission employeeMission;
	
	@ManyToOne
	@JoinColumn(name="employee")
	private Employee employee;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public EmployeeMission getEmployeeMission() {
		return employeeMission;
	}

	public void setEmployeeMission(EmployeeMission employeeMission) {
		this.employeeMission = employeeMission;
	}

	public String getMissionObjectif() {
		return missionObjectif;
	}

	public void setMissionObjectif(String missionObjectif) {
		this.missionObjectif = missionObjectif;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getAdvisorComment() {
		return advisorComment;
	}

	public void setAdvisorComment(String advisorComment) {
		this.advisorComment = advisorComment;
	}
	
	
}
