package com.mission.service;

import java.util.List;

import com.mission.domain.Employee;
import com.mission.domain.EmployeeMission;
import com.mission.domain.Mission;
import com.mission.domain.MissionReport;

public interface IActivityService {

	public void assignMissionToEmployee(String employeeId, int missionId);
	public List<EmployeeMission>empMissionList(int missionId);
	public Employee findEmployee(String empId);
	public void updateExperiedMission(int missionId, double percentage);
	public void startMission(int missionId);
	public void sendReport(MissionReport report, int employeeMissionId, String employeeId)throws Exception ;
	public void updateMissionObject(int missionObjectId, int percentage);
	public void traineeComment(String comment, String nationalId,int missionId);
	public void advisorAddComment(int missionReportId, String Comment);
	public void hrAddComment(int missionId, String comment);
	
}
