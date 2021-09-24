package com.mission.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;

@Entity
public class Mission {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String title;
	@FutureOrPresent(message="startdate can be in future or present")
	private Date startDate;
	@Future(message ="endDate can be in future time")
	private Date endDate;
	private Double cost;
	private String description;
	private boolean status;
	private Date createdDate;
	private double evaluationPercentage;
	
	@Column(length = 750)
	private String hrComment;
	
	@Enumerated(EnumType.STRING)
	private missionStatus mStatus;
	
	public static enum missionStatus{
		Start, Pending, Closed
	}
	

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Double getCost() {
		return cost;
	}
	public void setCost(Double cost) {
		this.cost = cost;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public missionStatus getmStatus() {
		return mStatus;
	}
	public void setmStatus(missionStatus mStatus) {
		this.mStatus = mStatus;
	}
	public double getEvaluationPercentage() {
		return evaluationPercentage;
	}
	public void setEvaluationPercentage(double evaluationPercentage) {
		this.evaluationPercentage = evaluationPercentage;
	}
	public String getHrComment() {
		return hrComment;
	}
	public void setHrComment(String hrComment) {
		this.hrComment = hrComment;
	}
	
	
}
