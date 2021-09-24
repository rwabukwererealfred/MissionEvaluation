package com.mission.dto;

public class MissionDto {

private int missionId;
private String comment;


public MissionDto() {
}

public MissionDto(int missionId, String comment) {
	
	this.missionId = missionId;
	this.comment = comment;
}


public int getMissionId() {
	return missionId;
}
public void setMissionId(int missionId) {
	this.missionId = missionId;
}
public String getComment() {
	return comment;
}
public void setComment(String comment) {
	this.comment = comment;
}


}
