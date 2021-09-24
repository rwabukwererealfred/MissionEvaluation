package com.mission.service;

import java.util.List;

import com.mission.domain.EmployeeMission;
import com.mission.domain.Fundings;
import com.mission.domain.Mission;
import com.mission.domain.MissionObjectif;
import com.mission.domain.MissionReport;
import com.mission.domain.Trainee;
import com.mission.domain.TraineeComment;

public interface IMissionServiceList {

	public List<EmployeeMission>MyAvailableMission(String employeeId, boolean missionStatus);
	public List<MissionObjectif>missionObjectifList(int missionId);
	public EmployeeMission findMissionById(int missionId);
	public List<MissionReport>missionReportByEmployee(int employeeMission);
	public List<MissionReport>missionReportedByToMe(int employeeMission);
	
	public List<Trainee>traineeList(int missionId);
	public List<TraineeComment>traineeCommentList(int missionId);
}
