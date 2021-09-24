package com.mission.service;

import java.util.List;

import com.mission.domain.Employee;
import com.mission.domain.Fundings;
import com.mission.domain.Mission;
import com.mission.domain.MissionObjectif;
import com.mission.domain.Trainee;

public interface IRegistrationService {

	public void registerEmployee(Employee employee);
	public void createMission(Mission mission);
	public void createMissionObjectif(MissionObjectif objectif, int missionId);
	public List<Employee>employeeList();
	public List<Mission>missionList();
	public List<MissionObjectif>missionObjectifList(int missionId);
	public void createFunds(Fundings fundings, int missionId);
	public List<Fundings>missionFundsList(int missionId);
	public void traineeRegistration(int missionId, Trainee trainee);
	
}

